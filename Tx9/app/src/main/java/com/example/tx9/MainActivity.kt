package com.example.tx9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val db = DatabaseHandler(this)
        val readBtn = findViewById<Button>(R.id.readBtn)
        insertBtn.setOnClickListener {
            val nameET = findViewById<EditText>(R.id.nameET)
            val yearET = findViewById<EditText>(R.id.birthYearET)
            if(nameET.text.toString().length > 0 && yearET.text.toString().length>0){
                val user = User(nameET.text.toString(),yearET.text.toString().toInt())
                db.insertUser(user)
                nameET.setText("")
                yearET.setText("")
                readBtn.performClick()
            }
            else{
                Toast.makeText(this,"Please fill content",Toast.LENGTH_SHORT).show()
            }
        }
        readBtn.setOnClickListener {
            val listOfUserTV = findViewById<TextView>(R.id.listOfUserTV)
            val userList = db.readData()
            listOfUserTV.text = ""
            for (i in 0 until userList.size){
                val user = userList.get(i)
                listOfUserTV.append(user.name + " - "+user.year + "\n")
            }
        }
        val updateBtn = findViewById<Button>(R.id.updateBtn)
        updateBtn.setOnClickListener {
            db.updateData()
            readBtn.performClick()
        }
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        deleteBtn.setOnClickListener {
            val nameET = findViewById<EditText>(R.id.nameET)
            db.deleteData(nameET.text.toString())
            readBtn.performClick()
        }

    }

}