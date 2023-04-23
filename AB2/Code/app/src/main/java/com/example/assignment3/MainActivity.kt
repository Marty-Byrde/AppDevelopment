package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.Services.ServiceInterface
import com.example.assignment3.TouchControl.SurfaceActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickListener = View.OnClickListener { element ->
            val button = element as Button;
            val text = button.text.toString()
            Log.d("APPDEV", "${text} has been pressed!")

            //* Cancel clickhandler
            if(!text.toLowerCase().startsWith("task")) return@OnClickListener;

            val task = text.split(" ")[1].toString().toInt();

            var intent = Intent(applicationContext, MainActivity::class.java);
            when(task){
                3 -> intent = Intent(applicationContext, ServiceInterface::class.java)
                4 -> intent = Intent(applicationContext, SurfaceActivity::class.java)
            }

            startActivity(intent)
        }


        val task1 = findViewById<Button>(R.id.task1)
        val task2 = findViewById<Button>(R.id.task2)
        val task3 = findViewById<Button>(R.id.task3)
        val task4 = findViewById<Button>(R.id.task4)

        task1.setOnClickListener(clickListener)
        task2.setOnClickListener(clickListener)
        task3.setOnClickListener(clickListener)
        task4.setOnClickListener(clickListener)


        task3.performClick()
    }


}