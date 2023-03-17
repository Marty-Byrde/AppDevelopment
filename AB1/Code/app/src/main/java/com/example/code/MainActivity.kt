package com.example.code

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    val locale_german: Locale = Locale("de")
    val locale_english: Locale = Locale("en")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtText : TextView = findViewById(R.id.txtText)
        val checkGerman: CheckBox = findViewById(R.id.checkGerman)
        val checkEnglish: CheckBox = findViewById(R.id.checkEnglish)


        updateLanguage()
        txtText.setText(R.string.message)
    }

    fun updateLanguage(){
        val currentLocale = applicationContext.resources.configuration
        Log.d("APD", currentLocale.toString())

        val config = Configuration()

    }




    fun LogLevels(){
        val tag = "AppD"

        Log.d(tag, "Thats a debug message!")
        Log.e(tag, "Thats a error message!")
        Log.i(tag, "Thats a info message!")
        Log.w(tag, "Thats a warning message!")
        Log.v(tag, "Thats a verbose message!")
    }

}