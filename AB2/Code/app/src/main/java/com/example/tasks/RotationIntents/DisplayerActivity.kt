package com.example.tasks.RotationIntents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tasks.R


class DisplayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayer)

        val txtLocation = findViewById<TextView>(R.id.txtLocation)
        txtLocation.text = intent.getStringExtra("location")

        val btnLocate = findViewById<Button>(R.id.btnLocate)
        btnLocate.setOnClickListener{
            val url = "geo:?q=${txtLocation.text}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

    }
}