package com.example.code

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    val locale_german: Locale = Locale("de")
    val locale_english: Locale = Locale("en")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtText: TextView = findViewById(R.id.txtText)
        val checkGerman: CheckBox = findViewById(R.id.checkGerman)
        val checkEnglish: CheckBox = findViewById(R.id.checkEnglish)

        // Select current language
        checkGerman.isChecked = applicationContext.resources.configuration.locale.toLanguageTag() == "de"
        checkEnglish.isChecked = applicationContext.resources.configuration.locale.toLanguageTag() == "en-US"


        checkGerman.setOnCheckedChangeListener { _, isChecked ->
            checkEnglish.isChecked = !isChecked;

            if (isChecked) {
                updateLocaleSettings(locale_german)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        checkEnglish.setOnCheckedChangeListener { _, isChecked ->
            checkGerman.isChecked = !isChecked;

            if (isChecked) {
                updateLocaleSettings(locale_english)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        txtText.setText(R.string.message)
    }

    private fun updateLocaleSettings(locale: Locale) {
        val config = applicationContext.resources.configuration
        config.setLocale(locale)
    }


    fun LogLevels() {
        val tag = "AppD"

        Log.d(tag, "Thats a debug message!")
        Log.e(tag, "Thats a error message!")
        Log.i(tag, "Thats a info message!")
        Log.w(tag, "Thats a warning message!")
        Log.v(tag, "Thats a verbose message!")
    }

}