package com.example.assignmentsheet5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmentsheet5.chatApp.ChatActivity
import com.example.assignmentsheet5.simpleListView.SimpleListViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        startActivity(Intent(this, UnitConverterActivity::class.java))
//        startActivity(Intent(this, SimpleListViewActivity::class.java))
        startActivity(Intent(this, ChatActivity::class.java))
    }
}