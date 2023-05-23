package com.example.ab7

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val txtInput: TextView = findViewById(R.id.txtInput)
        val txtResult: TextView = findViewById(R.id.txtResult)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val lblRate1: TextView = findViewById(R.id.lblRate1)
        val lblRate2: TextView = findViewById(R.id.lblRate2)

        val txtRate1: TextView = findViewById(R.id.txtRate1)
        val txtRate2: TextView = findViewById(R.id.txtRate2)

        lblRate1.visibility = View.INVISIBLE
        lblRate2.visibility = View.INVISIBLE
        txtRate1.visibility = View.INVISIBLE
        txtRate2.visibility = View.INVISIBLE

        val currencyOptions = EnumSet.allOf(Currencies::class.java).toArray()
        val options = ArrayList<String>()
        for(option in currencyOptions){
            for(convert in currencyOptions){
                if(option == convert) continue;
                options.add("$option to $convert")
            }
        }


        val selectionOptions: Array<String> = options.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectionOptions)
        spinner.adapter = adapter

        btnConvert.setOnClickListener {
            if(txtInput.text.toString().isBlank()){
                Snackbar.make(txtInput, "Missing input value!", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val selection = spinner.selectedItem.toString()
            val currencies = selection.split(" to ").toTypedArray()

            Thread {
                val data = API().getData(this, currencies)
                runOnUiThread{
                    setConversionRates(
                        currencies[0],
                        currencies[1],
                        data.getString(currencies[0]),
                        data.getString(currencies[1])
                    )

                    txtResult.text = String.format("%,.2f %s", txtInput.text.toString().toDouble() * data.getString(currencies[1]).toDouble(), currencies[1])
                }
            }.start()
        }
    }

    private fun setConversionRates(lRate1: String, lRate2: String, rate1: String, rate2: String){
        val lblRate1: TextView = findViewById(R.id.lblRate1)
        val lblRate2: TextView = findViewById(R.id.lblRate2)

        val txtRate1: TextView = findViewById(R.id.txtRate1)
        val txtRate2: TextView = findViewById(R.id.txtRate2)

        lblRate1.visibility = View.VISIBLE
        lblRate2.visibility = View.VISIBLE
        txtRate1.visibility = View.VISIBLE
        txtRate2.visibility = View.VISIBLE

        lblRate1.text = lRate1
        lblRate2.text = lRate2

        txtRate1.text = rate1
        txtRate2.text = rate2
    }

}