package com.example.assignmentsheet5.simpleListView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.assignmentsheet5.R
import com.google.android.material.snackbar.Snackbar

class SimpleListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list_view)

        val listView = findViewById<ListView>(R.id.listView)
        val selectionOptions: Array<String> = listOf("" +
                "Java",
                "JavaScript",
                "Kotlin",
                "Swift",
                "C",
                "C++",
                "C#",
                "Python",
                "Python1",
                "Python2",
                "Python3",
                "Python4",
                "Python5",
                "Python6",
                "Python7",
                "Python8",
                "Python9",
                "Python10",
                "Python11",
                "Python12",
                "Python13",
                "Python14",
                "Python15",
                "Python16",
                "Python17",
                "Python18",
                "Python19",
                "Python20",
                "Python21",
                "Python22",
                "Python23",
                "Python24",
                "Python25",
                "Python26",
                "Python27",
                "Python28",
                "Python29",
                "Python30",


        ).toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectionOptions)

        listView.adapter = adapter;

        listView.setOnItemClickListener { parent, view, position, id ->
            Snackbar.make(listView, "${selectionOptions[id.toInt()]} has been clicked!", Snackbar.LENGTH_SHORT).show()
        }

    }
}



data class CodeLanguage(val name: String, val description: String) {}