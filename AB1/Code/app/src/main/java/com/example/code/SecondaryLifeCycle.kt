package com.example.code

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondaryLifeCycle : AppCompatActivity() {
    private val activityIdentifier = "SecondaryLifecycle -"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary_life_cycle)

        Log.d("Lifecycle", "$activityIdentifier onCreate callback is called!")

        val btnBack: Button = findViewById(R.id.btnSwitchBack)
        btnBack.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })
    }


    override fun onStart() {
        Log.d("Lifecycle", "$activityIdentifier onStart callback is called!")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Lifecycle", "$activityIdentifier onResume callback is called!")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Lifecycle", "$activityIdentifier onPause callback is called!")
        super.onPause()
    }

    override fun onStop() {
        Log.d("Lifecycle", "$activityIdentifier onStop callback is called!")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Lifecycle", "$activityIdentifier onDestroy callback is called!")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("Lifecycle", "$activityIdentifier onRestart callback is called!")
        super.onRestart()
    }


}