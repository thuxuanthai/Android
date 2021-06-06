package com.example.tx12

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationBuilder: Notification.Builder
    lateinit var notificationChannel: NotificationChannel
    private val channelID = "vn.udn.vku.nhhai.vkun5l12_notification"
    private val channelDescription = "VKU N5 Notification Demo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainIntent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,mainIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        val notifyBtn = findViewById<Button>(R.id.notifyBtn)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelID,channelDescription,
                        NotificationManager.IMPORTANCE_DEFAULT)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)
                notificationBuilder = Notification.Builder(this,channelID)
                notificationBuilder.setContentTitle("VKU Notification Title")
                        .setContentText("VKU Notification Content")
                        .setSmallIcon(R.drawable.ic_launcher_round)
                        .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_round))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
            }
            else{
                notificationBuilder = Notification.Builder(this)
                notificationBuilder.setContentTitle("VKU Notification Title")
                        .setContentText("VKU Notification Content")
                        .setSmallIcon(R.drawable.ic_launcher_round)
                        .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_round))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
            }
            notificationManager.notify(6789,notificationBuilder.build())
        }
    }
}