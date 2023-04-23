package com.example.assignment3.Services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.assignment3.R


class ServiceInterface : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_interface)

        val btnStart = findViewById<Button>(R.id.btnStartService)
        val btnStop = findViewById<Button>(R.id.btnStopService)
        
    }
}