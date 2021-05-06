package com.example.exercise2_quocgia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val contryS= listOf<countryInfo>(
        countryInfo("Viet Nam", 96.5,R.drawable.vietnam),
        countryInfo("Thai Lan", 59.4,R.drawable.thailand),
        countryInfo("Korea", 62.8,R.drawable.korea),
        countryInfo("Japan", 105.9,R.drawable.japan),
        countryInfo("Lao", 12.6,R.drawable.laos)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryAdapter=countryAdapter(this,contryS)
        countryLV.adapter=countryAdapter

    }
}