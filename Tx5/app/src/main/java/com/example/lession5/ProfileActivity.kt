package com.example.lession5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    val IMG_PICK_CODE = 100  // Mã yêu cầu
    var imgURI: Uri? = null  // upload file
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (intent != null) {     // intent mặc định hỗ trợ trong Android
            val username = intent.getStringExtra("usernameValue")  // Biến name chung
            findViewById<TextView>(R.id.usernameTV).text = username?.capitalize()  //capitalize: viết hoa
        }
        findViewById<Button>(R.id.backBtn).setOnClickListener {
            val intent=Intent()
            if(imgURI != null){
                intent.putExtra("imgURI",imgURI.toString())   // toString : ép kiểu
            }else{
                intent.putExtra("imgURI","")
            }
            setResult(Activity.RESULT_OK,intent)  // lấy kết quả trả về từ 1 activity
            finish()        // kết thúc quá trình lấy dữ liệu.. bắt buộc pải có setResult và finish
        }
        findViewById<Button>(R.id.sendEmailBtn).setOnClickListener {
            val mIntent= Intent(Intent.ACTION_SEND)  //Action_Send (khi có một đính kèm)
            mIntent.data=Uri.parse("mailto: ")
            mIntent.type="text/plain"               // định dạng
            mIntent.putExtra(Intent.EXTRA_TEXT,"This is a great mail from VKU") // nội dung của mail
            mIntent.putExtra(Intent.EXTRA_SUBJECT,"Important mail")     // chủ đề của mail
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("tttxuan.19it5@vku.udn.vn","xuanqn01@gmail.com")) // 1 mảng String chứa địa chỉ email của tất cả người nhận
            startActivity(Intent.createChooser(mIntent,"Send email via..."))
        }

        findViewById<Button>(R.id.chooseLogoBtn).setOnClickListener {
            val iIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(iIntent,IMG_PICK_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMG_PICK_CODE && resultCode==Activity.RESULT_OK){
            imgURI = data?.data
            findViewById<ImageView>(R.id.profileLogoIV).setImageURI(imgURI)
        }
    }
}