package com.example.tasks.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tasks.R

class FirstFragment : Fragment(R.layout.first_fragment) {
    var parent : FragmentActivity? = null;
    private lateinit var _view : View;

    fun createLink(activity:FragmentActivity){
        parent = activity;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this._view = view;
        var count = 0;

        val button = view.findViewById<Button>(R.id.btnRespondF1)
        button.setOnClickListener{ parent?.receiveMessage(" ${javaClass.simpleName} has responded ${count++} times!")}
    }


    fun receiveMessage(message: String){
        Log.d("AppDev", "Fragment has received a message! $message")
        this._view.findViewById<TextView>(R.id.txtCommunicateF2).text = message
    }
}