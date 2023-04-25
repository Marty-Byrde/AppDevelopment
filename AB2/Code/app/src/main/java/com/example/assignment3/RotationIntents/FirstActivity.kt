package com.example.assignment3.RotationIntents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.assignment3.R

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val txtField = findViewById<EditText>(R.id.txtInput)

        btnSearch.setOnClickListener{
            val intent = Intent(this, DisplayerActivity::class.java)
            intent.putExtra("location", txtField.text.toString())
            startActivity(intent)
        }
    }
}