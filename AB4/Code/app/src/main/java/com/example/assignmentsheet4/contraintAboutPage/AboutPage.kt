package com.example.assignmentsheet4.contraintAboutPage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.assignmentsheet4.R

class AboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)

        val email = findViewById<ImageView>(R.id.icon1)
        val instgram = findViewById<ImageView>(R.id.icon2)
        val twitter = findViewById<ImageView>(R.id.icon3)
        val btnClose = findViewById<Button>(R.id.btnclose)

        twitter.setOnClickListener {
            val url = "https://twitter.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        instgram.setOnClickListener {
            val url = "https://www.instagram.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:somemail@gmx.at")
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            finish()
        }
    }
}