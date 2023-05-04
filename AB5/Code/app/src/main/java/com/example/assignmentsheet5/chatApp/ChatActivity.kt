package com.example.assignmentsheet5.chatApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsheet5.R
import java.util.*

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val messages = Array(10) {
            Message("Herbert", Date(), "Hey whats up?", MessageType.IN);
            Message("Hubert", Date(), "Not much, how are you doing?", MessageType.IN);
            Message("Herbert", Date(), "Great!, I hope you too?", MessageType.OUT);
            Message("Hubert", Date(), "Yeah, just just bought a new house", MessageType.IN);
            Message("Herbert", Date(), "Wow. Where is it located?", MessageType.OUT);
        }

        Log.d("AppDev", "Passing ${messages.size} ")
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MessageAdapter(messages)




    }
}