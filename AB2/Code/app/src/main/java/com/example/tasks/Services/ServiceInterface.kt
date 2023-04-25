package com.example.tasks.Services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tasks.R


class ServiceInterface : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_interface)

        val btnStart = findViewById<Button>(R.id.btnStartService)
        val btnStop = findViewById<Button>(R.id.btnStopService)

        btnStart.setOnClickListener {
            startService(Intent(this, AudioService::class.java))
        }

        btnStop.setOnClickListener {
            stopService(Intent(this, AudioService::class.java))
        }

    }
}