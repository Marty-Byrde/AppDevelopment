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
    fun getData(activity: Activity) : JSONObject{
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
            return JSONObject(stringBuffer.toString())
        } catch (e: Exception) {
            return fetch(activity)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun fetch(activity: Activity) : JSONObject {
        val href = "https://api.freecurrencyapi.com/v1/latest?apikey=${ENV.API_KEY}&currencies=EUR%2CUSD"
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

        return json;
    }
}