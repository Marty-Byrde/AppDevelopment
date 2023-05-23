package com.example.ab7

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
    @SuppressLint("SimpleDateFormat")
    fun getData(activity: Activity, currencies: Array<String>) : JSONObject{
        val formatter = SimpleDateFormat("dd_MM_yyyy")
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

        val formatter = SimpleDateFormat("dd_MM_yyyy")
        val fileName = "store_data_${formatter.format(Date())}.json"
        val outputStream: FileOutputStream
        try {
            outputStream = activity.openFileOutput(fileName, AppCompatActivity.MODE_PRIVATE)
            outputStream.write(jsonStr.toByteArray())
            outputStream.close()
            Log.d("Fetch-JSON", "Saved api response!")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return json.getJSONObject("data");
    }
}