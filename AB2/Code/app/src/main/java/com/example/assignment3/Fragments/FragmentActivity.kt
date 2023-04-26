package com.example.assignment3.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.assignment3.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val btnSwitchFragment1 = findViewById<Button>(R.id.btnStartF1)
        val btnSwitchFragment2 = findViewById<Button>(R.id.btnStartF2)
        val btnCommunicate = findViewById<Button>(R.id.btnCommunicateFragment)
        val txtMessage = findViewById<TextView>(R.id.txtMessage)

        txtMessage.visibility = View.INVISIBLE
        btnCommunicate.visibility = View.INVISIBLE;


        val linearLayout = findViewById<LinearLayout>(R.id.layoutLinear)

        val f1 = FirstFragment()
        val f2 = SecondFragment()
        var count = 0;

        f1.createLink(this)
        f2.createLink(this)



        btnSwitchFragment1.setOnClickListener{
            Log.d("AppDev", "Setting fragment 1")
            count = 0;
            btnCommunicate.visibility = View.VISIBLE;

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                remove(f2)
                remove(f1)
                add(R.id.layoutLinear, f1)
            }
        }

        btnSwitchFragment2.setOnClickListener{

            Log.d("AppDev", "Setting fragment 2")
            count = 0;
            btnCommunicate.visibility = View.VISIBLE;

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                remove(f1)
                remove(f2)
                add(R.id.layoutLinear, f2)
            }
        }

        btnCommunicate.setOnClickListener{
            if(f1.isAdded) {
                Log.d("AppDev", "Communicating with Fragment 1 from MainActivity")
                f1.receiveMessage("Communicated ${count++} amount of times with this Fragment!")
            }
            if(f2.isAdded) {
                Log.d("AppDev", "Communicating with Fragment 2 from MainActivity")
                f2.receiveMessage("Communicated ${count++} amount of times with this Fragment!")
            }
        }

    }

    fun receiveMessage(message: String){
        Log.d("AppDev", "Main Activity received some text: $message")
        val messageField = findViewById<TextView>(R.id.txtMessage)
        messageField.visibility = View.VISIBLE
        messageField.text = message;

    }

}