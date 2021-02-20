package com.emon.workmanager

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.emon.workmanager.App.Companion.CHANNEL_ID


class ExampleService: Service() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        Handler(Looper.getMainLooper()).postDelayed({

            val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<SampleClass>().build()
            WorkManager.getInstance(this).enqueue(uploadWorkRequest)

        },10000)


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)

            notification.setContentTitle("Example Service")
            .setContentText("ForeGround With Work Manager")
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        Handler(Looper.getMainLooper()).post {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                notificationManager.createNotificationChannel(channel)
                notification.setChannelId(CHANNEL_ID)

                startForeground(1, notification.build())
            }

            startForeground(1, notification.build())
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}