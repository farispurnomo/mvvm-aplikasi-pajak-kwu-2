package com.example.tugas.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.tugas.R
import com.example.tugas.ui.auth.login.LoginActivity
import com.example.tugas.utils.log.Logger
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageService : FirebaseMessagingService() {

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Logger.d("FirebaseMessageService", "Refreshed token: $s")
//        println("NEW_TOKEN")
//        println(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
//        println("masuk1")
        println(remoteMessage.data)
        println(remoteMessage.from)
        println(remoteMessage.notification?.title)
        println(remoteMessage.notification?.body)
        println(remoteMessage.notification?.tag)
        println(remoteMessage.notification?.tag)
//        val remoteData = remoteMessage
//        var data=JSONObject(remoteData)

//        var detailnop = remoteData.notification
//        var gson = Gson()
//        var detailNopJson = JSONObject(gson.toJson(remoteData))
//        println("notifdata0")
//        println(detailNopJson.toString())
//        println("notifdata1")
//        for (attributDetailNopJson in detailNopJson.keys()) {
//            val valueDetailNopJson = detailNopJson.get(attributDetailNopJson.toString())
//            println(attributDetailNopJson)
//            println(valueDetailNopJson)
//            println("____________________")
//        }
//        println(detailNopJson)
//        println("notifdata2")
//        println(remoteData.toString())

//        val intent = Intent("custom-event-name")
//        intent.putExtra("notif_count", remoteData.size.toString());
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


        showNotification(remoteMessage)

//        var loginRepository = LoginRepository(super.getApplicationContext() as Application)
//        loginRepository.updateNotifPlus()
//        println("broadcastmama")
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showNotification(remoteM: RemoteMessage) {
        val bundle = Bundle()
//        bundle.putString("msg", "iya")
//        bundle.putString("nop",  data.getString("nop"))
//        bundle.putString("tahun",  data.getString("tahun"))

        val mIntent = Intent(this, LoginActivity::class.java)
            .putExtras(bundle)

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this,
                0,
                mIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getActivity(
                this,
                0,
                mIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val bigText = NotificationCompat.BigTextStyle()
            .setBigContentTitle(remoteM.notification?.title)
            .bigText(remoteM.notification?.body)
//            .setSummaryText(remoteM.data["summary"])

        val mBuilder = NotificationCompat.Builder(
            applicationContext, getString(R.string.default_notification_channel_id)
        ).setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_logo_secondary)
            .setContentTitle(remoteM.notification?.title)
            .setContentText(remoteM.notification?.body)
            .setAutoCancel(true)
            .setStyle(bigText)
//            .setDefaults(Notification.DEFAULT_ALL)
//            .setDefaults(Notification.DEFAULT_SOUND)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 100, 200))
            .setLights(Color.RED, 1000, 1000)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        val mNotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.default_notification_channel_id),
                "Channel Pesanan",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(0, 100, 200)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            mNotificationManager.createNotificationChannel(channel)
        }

        val notify = mBuilder.build()
        val time = System.currentTimeMillis()
        mNotificationManager.notify(time.toInt(), notify)
    }

}