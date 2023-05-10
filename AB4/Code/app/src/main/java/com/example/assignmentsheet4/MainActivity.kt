package com.example.assignmentsheet4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmentsheet4.contraintAboutPage.AboutPage
import com.example.assignmentsheet4.gestures.GesturesMain
import com.example.assignmentsheet4.layouts.LayoutActivity
import com.example.assignmentsheet4.personalWebBrowser.PersonBrowserActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        startActivity(Intent(this, GesturesMain::class.java)) // 1
        startActivity(Intent(this, LayoutActivity::class.java)) // 2
//        startActivity(Intent(this, AboutPage::class.java)) // 3
//        startActivity(Intent(this, PersonBrowserActivity::class.java)) // 4
    }
}