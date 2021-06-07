package com.example.bai11

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

private const val CAMERA_STORAGE_PERMISSION_CODE = 10

class MainActivity : AppCompatActivity() {

    val IMG_PICK_CODE = 100
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null


    private var permissionArray = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestBtn = findViewById<Button>(R.id.requestBtn)
        val cameraBtn = findViewById<Button>(R.id.cameraBtn)
        val openFileBtn = findViewById<Button>(R.id.openFileBtn)
        cameraBtn.setOnClickListener {
            openCamera()
        }
        openFileBtn.setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.type = "*/*"
//            startActivity(intent)

            val iIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            iIntent.type = "*/*"
            startActivityForResult(iIntent,IMG_PICK_CODE)
        }
        requestBtn.setOnClickListener {
            if(checkPermissions(this, permissionArray)){
                Toast.makeText(this,"All permissions are already granted", Toast.LENGTH_SHORT).show()
            }
            else {
                requestPermissions(permissionArray, CAMERA_STORAGE_PERMISSION_CODE)
            }
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"New picture")
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the Camera")

  //      image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE)
    }

    private fun checkPermissions(context: Context, permissionArray: Array<String>):Boolean{
        var isAllGranted = true
        for (i in permissionArray.indices){
            if(checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED){
                isAllGranted = false
            }
        }
        return isAllGranted
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var isAllGranted = true
        if(requestCode == CAMERA_STORAGE_PERMISSION_CODE){
            for (i in permissions.indices){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    isAllGranted = false
                    val canRequestAgainLater = shouldShowRequestPermissionRationale(permissions[i])
                    if(canRequestAgainLater){
                        Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this,"Go to settings to enable permission"
                                , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        if(isAllGranted){
            Toast.makeText(this,"All permissions granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val imageView : ImageView = findViewById(R.id.img_view)
            imageView.setImageURI(image_uri)
        }
        if(requestCode == IMG_PICK_CODE && resultCode == Activity.RESULT_OK){
            image_uri = data?.data
            findViewById<ImageView>(R.id.img_view).setImageURI(image_uri)
        }
    }
}