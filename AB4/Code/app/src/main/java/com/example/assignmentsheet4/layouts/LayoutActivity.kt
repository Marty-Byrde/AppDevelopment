package com.example.assignmentsheet4.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.assignmentsheet4.R

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        val btnConstraint = findViewById<Button>(R.id.buttonConstraintLayout)
        val btnLinear = findViewById<Button>(R.id.buttonLinearLayout)


        btnConstraint.setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }
        btnLinear.setOnClickListener {
            startActivity(Intent(this, LinearLayoutActivity::class.java))
        }
    }
}