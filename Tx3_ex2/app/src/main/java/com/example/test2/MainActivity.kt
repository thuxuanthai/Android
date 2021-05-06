package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarFB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textFB.text=progress.toString()+"%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@MainActivity, "Progress is: " + seekBarSW.progress + "%", Toast.LENGTH_SHORT).show()
            }
        })
        seekBarIG.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textIG.text=progress.toString()+"%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@MainActivity, "Progress is: " + seekBarSW.progress + "%", Toast.LENGTH_SHORT).show()
            }
        })

        seekBarSW.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textSW.text=progress.toString()+"%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@MainActivity, "Progress is: " + seekBarSW.progress + "%", Toast.LENGTH_SHORT).show()
            }
        })


        imgBtnFull.setOnClickListener{
            seekBarFB.progress = 100
            seekBarIG.progress = 100
            seekBarSW.progress = 100
        }
        imgBtnClearn.setOnClickListener{
            seekBarFB.progress = 0
            seekBarIG.progress = 0
            seekBarSW.progress = 0
        }

        registerForContextMenu(btnSP)

//        val showActivity = findViewById<Button>(R.id.btnSP)
//        showActivity.setOnClickListener {
//            val intent = Intent(this,layout2::class.java)
//            startActivity(intent)
//        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.show_menu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override  fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.say_item ->{
                imageView2.isVisible=true
                imageView2.setImageResource(R.drawable.h3)
                Toast.makeText(this,"Hoa hồng vàng",Toast.LENGTH_LONG).show()
            }
            R.id.hide_item ->{
                imageView2.isVisible=true
                imageView2.setImageResource(R.drawable.h2)
                Toast.makeText(this,"Tím mộng mơ",Toast.LENGTH_LONG).show()
            }
            R.id.show_item -> {
                imageView2.isVisible=true
                imageView2.setImageResource(R.drawable.h1)
                Toast.makeText(this,"Hoa hồng tím",Toast.LENGTH_LONG).show()
            }
            R.id.an_item ->{
                imageView2.isVisible=false
                Toast.makeText(this,"Đã ẩn",Toast.LENGTH_LONG).show()
            }

        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        R.id.idHalf ->{
            seekBarFB.progress=50
            seekBarIG.progress=50
            seekBarSW.progress=50
        }
        R.id.idFull ->{
            seekBarFB.progress=100
            seekBarIG.progress=100
            seekBarSW.progress=100
        }
    }
        return super.onOptionsItemSelected(item)
    }
}