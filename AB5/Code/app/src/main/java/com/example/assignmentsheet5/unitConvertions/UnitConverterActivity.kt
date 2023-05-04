package com.example.assignmentsheet5.unitConvertions

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentsheet5.R


class UnitConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_converter)

        val selection = findViewById<Spinner>(R.id.spSelection)
        val txtInput = findViewById<EditText>(R.id.txtInput)
        val txtOutput = findViewById<EditText>(R.id.txtOutput)
        val btnConvert = findViewById<Button>(R.id.btnConvert)

        val selectionOptions: Array<String> = listOf("meter to cm", "inch to cm", "cm to inch", "cm to meter").toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectionOptions)
        selection.adapter = adapter


        btnConvert.setOnClickListener {
            when (selection.selectedItem.toString()) {
                "meter to cm" -> txtOutput.setText((txtInput.text.toString().toDouble() * 100).toString())
                "cm to m" -> txtOutput.setText((txtInput.text.toString().toDouble() / 100).toString())
                "cm to inch" -> txtOutput.setText((txtInput.text.toString().toDouble() / 2.54).toString())
                "inch to cm" -> txtOutput.setText((txtInput.text.toString().toDouble() * 2.54).toString())
            }
        }


    }
}