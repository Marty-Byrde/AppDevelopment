package com.example.ab7

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ab7.database.DBHandler
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*


class API {
    private val formatter = SimpleDateFormat("dd_MM_yyyy")

    /**
     * This function returns a [JSONObject] that has been stored locally (file) or remote (db), based on the configuration [MainActivity.local_storage].
     */
    fun getData(activity: Activity, currencies: Array<String>) : JSONObject{
        val main = activity as MainActivity

        if(main.local_storage) return getLocalData(activity, currencies)

        return getDBData(activity, currencies)
    }

    /**
     * This function returns a [JSONObject] that matches the requested [currencies], which has been stored.
     * If no [JSONObject] is found a new fetch-request is made using the api. Its content is then properly stored and returned.
     *
     * @return A [JSONObject] that has been either stored locally or fetched using the api.
     */
    @SuppressLint("SimpleDateFormat")
    private fun getLocalData(activity: Activity, currencies: Array<String>) : JSONObject{
        val fileName = "store_data_${formatter.format(Date())}.json"
        val stringBuffer = StringBuffer()
        val fileInputStream: FileInputStream

        try {
            fileInputStream = activity.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuffer.append(line)
            }
            fileInputStream.close()
            Log.d("Fetch-JSON", "Loaded locally stored data")

            val obj = JSONObject(stringBuffer.toString())
            val rates = obj.getJSONObject("data")
            if(!rates.has(currencies[0])) return fetch(activity, currencies)
            if(!rates.has(currencies[1])) return fetch(activity, currencies)
            return obj.getJSONObject("data")
        } catch (e: Exception) {
            return fetch(activity, currencies)
        }
    }

    /**
     * This function stores a [JSONObject] locally, in a file.
     */
    private fun storeLocal(activity: Activity, obj: JSONObject){
        val fileName = "store_data_${formatter.format(Date())}.json"
        val outputStream: FileOutputStream
        try {
            outputStream = activity.openFileOutput(fileName, AppCompatActivity.MODE_PRIVATE)
            outputStream.write(obj.toString().toByteArray())
            outputStream.close()
            Log.d("Fetch-JSON", "Saved api response!")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun fetch(activity: Activity, currencies: Array<String>) : JSONObject {
        val href = "https://api.freecurrencyapi.com/v1/latest?base_currency=${currencies[0]}&apikey=${ENV.API_KEY}&currencies=${currencies.joinToString("%2C")}"
        val url = URL(href)
        val builder = StringBuilder()
        BufferedReader(InputStreamReader(url.openStream(), StandardCharsets.UTF_8)).use { reader ->
            var str: String?
            while (reader.readLine().also { str = it } != null) {
                builder.append(str)
            }
        }
        val jsonStr = builder.toString()

        Log.d("Fetch-JSON", "Successfully received data from API.")
        val json = JSONObject(jsonStr)
        Log.d("Fetch-JSON", "$json")

        val main = activity as MainActivity
        if(main.local_storage) storeLocal(activity, json)
        else storeDB(activity, json)

        return json.getJSONObject("data");
    }


    /**
     * This function stores a given [JSONObject] in the database, with the current [Date]
     */
    @SuppressLint("Recycle")
    private fun storeDB(activity: Activity, obj: JSONObject) : Long{
        val handler = DBHandler(activity);
        val db = handler.writableDatabase

        val values = ContentValues().apply {
            put(DBHandler.DBEntry.dateColumnTitle, formatter.format(Date()))
            put(DBHandler.DBEntry.jsonColumnTitle, obj.toString())
        }
        Log.d("Fetch-JSON-DB", "Saving factors in the database!")
        return db.insert(DBHandler.DBEntry.table_name, null, values)
    }

    /**
     * This function checks whether a [JSONObject], that matches the requested [currencies], which has been inserted today, is in the database.
     *
     * @return A [JSONObject] that matches the requested criteria. In case there is no match, a fetch request using the [API] will be initiated and its conents are stored and returned.
     */
    private fun getDBData(activity: Activity, currencies: Array<String>) : JSONObject{
        val handler = DBHandler(activity);
        val db = handler.writableDatabase

        val projection = arrayOf(BaseColumns._ID, DBHandler.DBEntry.dateColumnTitle, DBHandler.DBEntry.jsonColumnTitle)
        val selection = "${DBHandler.DBEntry.dateColumnTitle} = ?"
        val selectionArgs = arrayOf(formatter.format(Date()))

        val rows: Cursor = db.query(DBHandler.DBEntry.table_name, projection, selection, selectionArgs, null, null, null)
        while(rows.moveToNext()){
            val jsonStr = rows.getString(rows.getColumnIndex(DBHandler.DBEntry.jsonColumnTitle) ?: 0)
            if(jsonStr.toString().isBlank()) break;

            Log.d("Fetch-JSON-DB", "I have retrieved the json-factors from the database!")
            val json = JSONObject(jsonStr)

            if(!json.has(currencies[0])) break;
            if(!json.has(currencies[1])) break;

            return json
        }

        return fetch(activity, currencies)
    }

}