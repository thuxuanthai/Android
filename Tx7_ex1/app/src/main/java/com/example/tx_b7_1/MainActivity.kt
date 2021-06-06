package com.example.tx_b7_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Switch
import androidx.core.view.isVisible
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var progressbar1: ProgressBar
    lateinit var progressbar2: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart=findViewById<Button>(R.id.button)
        progressbar1=findViewById(R.id.progressBar2)
        progressbar2=findViewById(R.id.progressBar)
        btnStart.setOnClickListener {
            progressbar1.progress=0
            progressbar2.progress=0
            go()
        }

    }
    fun go(){
        var executor=Executors.newSingleThreadExecutor()
        var handler = Handler(Looper.getMainLooper())
        var sw =findViewById<Switch>(R.id.switch1)

        executor.execute{
            for(i in 1..10){
                Thread.sleep(1000)
                handler.post{
                    progressbar1.progress = 10*i
                }
                if(sw.isChecked){
                    Thread.sleep(5000)
                }
            }

        }
    }
}