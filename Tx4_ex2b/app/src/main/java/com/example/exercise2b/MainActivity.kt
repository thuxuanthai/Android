package com.example.exercise2b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val countrySource = listOf<countryInfo>(
        countryInfo("Viet Nam",96.5,R.drawable.vietnam),
        countryInfo("Thailand",59.4,R.drawable.thailand),
        countryInfo("Laos",12.6,R.drawable.laos),
        countryInfo("Korea",62.8,R.drawable.korea),
        countryInfo("Japan",105.9,R.drawable.japan)

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryAdapter=countryRV(this,countrySource)
        coutryrecycle.layoutManager= GridLayoutManager(this,2)
        coutryrecycle.adapter=countryAdapter
    }
}