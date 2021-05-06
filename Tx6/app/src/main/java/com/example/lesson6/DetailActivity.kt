package com.example.lesson6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if(intent!=null){
            detailtTitle.text=intent.getStringExtra("title")
            detailDesc.text=intent.getStringExtra("title")
            Picasso.get().load(intent.getStringExtra("imgURI")).into(detailImage)
        }
    }
}