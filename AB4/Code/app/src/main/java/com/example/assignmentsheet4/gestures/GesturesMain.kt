package com.example.assignmentsheet4.gestures

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsheet4.R
import com.google.android.material.snackbar.Snackbar

class GesturesMain : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestures_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recView)
        val btnSwitch = findViewById<Button>(R.id.btnTrack)
        val snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), "Default Text", Snackbar.LENGTH_SHORT        )

        btnSwitch.setOnClickListener {
            startActivity(Intent(this, TrackGestures::class.java))
        }

        recyclerView.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_POINTER_DOWN) {
                snackbar.setText("User touched the layout with another pointer")
            }
            if (motionEvent.action == MotionEvent.ACTION_POINTER_UP) {
                snackbar.setText("User lifted the other pointer off the layout")
            }
            if (motionEvent.action == MotionEvent.ACTION_OUTSIDE) {
                snackbar.setText("User moved outside while pressing down on the layout")
            }
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                snackbar.setText("User touched the layout")
            }
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                snackbar.setText("User moved while pressing down the layout")
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                snackbar.setText("User lifted his finger off the layout")
            }
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                snackbar.setText("User canceled the touch event")
            }

            snackbar.show()

            return@setOnTouchListener true
        }
    }
}