package com.example.tx11

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

private const val CAMERA_STORAGE_PERMISSION_CODE = 10
class MainActivity : AppCompatActivity() {
    private var permissionArray = arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val requestBtn = findViewById<Button>(R.id.requestBtn)
        val cameraBtn = findViewById<Button>(R.id.cameraBtn)
        val openFileBtn = findViewById<Button>(R.id.openFileBtn)
        cameraBtn.setOnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }
        openFileBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivity(intent)
        }
        requestBtn.setOnClickListener {
            if(checkPermissions(this, permissionArray)){
                Toast.makeText(this,"All permissions are already granted",Toast.LENGTH_SHORT).show()
            }
            else {
                requestPermissions(permissionArray, CAMERA_STORAGE_PERMISSION_CODE)
            }
        }
    }

    private fun checkPermissions(context: Context,permissionArray: Array<String>):Boolean{
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
                        Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this,"Go to settings to enable permission"
                                ,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        if(isAllGranted){
            Toast.makeText(this,"All permissions granted",Toast.LENGTH_SHORT).show()
        }
    }
}