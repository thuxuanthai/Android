package com.example.tx7_ex2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startButton)
        val stopBtn = findViewById<Button>(R.id.stopButton)
        timePicker = findViewById<TimePicker>(R.id.timePicker)
        var alarmIntent = Intent(this,AlarmService::class.java)

        startBtn.setOnClickListener{
            val hour = timePicker.hour
            val minute = timePicker.minute
            alarmIntent.putExtra("hour",hour)
            alarmIntent.putExtra("minute",minute)
            startService(alarmIntent)
        }
        stopBtn.setOnClickListener{
            stopService(alarmIntent)
        }
    }
}