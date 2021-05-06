package com.example.tx4_2a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.tx4_2a.CountryAdapter
import com.example.tx4_2a.CountryInfo

class MainActivity : AppCompatActivity() {
    val countrySource = listOf<CountryInfo>(
            CountryInfo("Viet Nam",96.5,R.drawable.vietnam),
            CountryInfo("Thailand",59.4,R.drawable.thailand),
            CountryInfo("Korea",62.8,R.drawable.korea),
            CountryInfo("Japan",105.9,R.drawable.japan),
            CountryInfo("Laos",12.6,R.drawable.laos)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.countryListView)
        val countryAdapter = CountryAdapter(this,countrySource)
        listView.adapter = countryAdapter
    }
}