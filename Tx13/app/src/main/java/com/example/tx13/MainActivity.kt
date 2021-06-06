package com.example.tx13

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

const val CONTACT_PERMISSION_CODE = 100
class MainActivity : AppCompatActivity() {
    private var permissionArray = arrayOf(Manifest.permission.READ_CONTACTS)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.getContactBtn)
        btn.setOnClickListener {
            if(checkCallingOrSelfPermission(Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED) {
                getContacts()
            }
            else{
                requestPermissions(permissionArray, CONTACT_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CONTACT_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getContacts()
            }
            else{
                Toast.makeText(this,"Please grant us permission to read contacts for this function to work",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getContacts(){
        val contactTV = findViewById<TextView>(R.id.contactListTV)
        contactTV.text = ""
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null,null,null,null)
        if(cursor != null && cursor.moveToFirst()){
            do{
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                contactTV.append(name.toString()+"\n")
            }
            while (cursor.moveToNext())
            cursor.close()
        }
    }
}