package com.example.lession5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    val PROFILE_ACTIVITY_CODE =101      //Mã yêu cầu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn.setOnClickListener {
            val username= findViewById<EditText>(R.id.usernameET).text.toString()       //trả về giá trị của đối tượng
            val password= findViewById<EditText>(R.id.passwordET).text.toString()
            if(username.indexOf("a")==0 && password=="123"){                      //kiểm tra chuỗi có chứa "a"
                val intent=Intent(applicationContext,ProfileActivity::class.java)        //Intent :sử dụng để yêu cầu một thành phần ứng dụng khác thực hiện một hành động
                intent.putExtra("usernameValue",username)
                startActivityForResult(intent,PROFILE_ACTIVITY_CODE)
            }else{
                loginTV.text="Wrong password, try again!"
            }
        }
        val url="https://coolfont.org/images/appple-store-img.jpg"
        Picasso.get().load(url).into(mainActivityLogoIV)
    }

    override fun onResume() {      // khởi động lại activity đã tạm dừng
        super.onResume()
        usernameET.setText("")
        passwordET.setText("")
        loginTV.text="Please login"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  // dữ liệu trả về
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PROFILE_ACTIVITY_CODE) {
            val imgURL=data?.getStringExtra("imgURI")
            if(imgURL!=null && imgURL!=""){
                mainActivityLogoIV.setImageURI(Uri.parse(imgURL))
            }
        }
        }
}