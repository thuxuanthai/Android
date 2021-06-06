package com.example.tx8

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = FirstFragment()
        val fragment2 = SecondFragment()

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        btn1.setBackgroundColor(Color.CYAN)
        btn2.setBackgroundColor(Color.CYAN)

        btn1.setOnClickListener{
            btn1.setBackgroundColor(Color.YELLOW)
            btn2.setBackgroundColor(Color.CYAN)
            supportFragmentManager.popBackStackImmediate()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,fragment1)
                addToBackStack(null)
                commit()
            }
        }

        btn2.setOnClickListener{
            btn1.setBackgroundColor(Color.CYAN)
            btn2.setBackgroundColor(Color.YELLOW)
            supportFragmentManager.popBackStackImmediate()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,fragment2)
                addToBackStack(null)
                commit()
            }
        }

    }
}