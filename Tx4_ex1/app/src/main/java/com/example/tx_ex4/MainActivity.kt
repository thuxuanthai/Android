package com.example.tx_ex4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listView = mutableListOf<String>("Con Chó","Con Mèo", "Con Heo")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var view=findViewById<ListView>(R.id.country)
        var arrayAdapter=ArrayAdapter<String>(this,R.layout.row,R.id.countryName,listView)
        view.adapter=arrayAdapter

        var Add_item=findViewById<Button>(R.id.btnAdd)
        var Cl_item=findViewById<Button>(R.id.btnClear)

        Add_item.setOnClickListener {
            var i=listView.size
            listView.add(i,"gsgsgsg")
            arrayAdapter.notifyDataSetChanged()
        }
        Cl_item.setOnClickListener {
            arrayAdapter.clear()
        }
    }


}