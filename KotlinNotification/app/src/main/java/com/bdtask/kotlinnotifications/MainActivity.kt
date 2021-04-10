package com.bdtask.kotlinnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.*


class MainActivity : AppCompatActivity() {

    var CHANNEL_ID_ANDROID = "com.bdtask.kotlinnotifications.ANDROID"
    var CHANNEL_NAME = "ANDROID_CHANNEL"
    val NOTIFICATION_ID = 2
    val ACTION_NOTIFICATION_ID = 2
    val ACTION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ////////Simple Notification////////

        val SimpleNotificationButton = findViewById<Button>(R.id.simple_notification)

        SimpleNotificationButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val imp = NotificationManager.IMPORTANCE_HIGH
                val mNotificationChannel =
                    NotificationChannel(CHANNEL_ID_ANDROID, CHANNEL_NAME, imp)
                val notificationBuilder: Notification.Builder =
                    Notification.Builder(this, CHANNEL_ID_ANDROID)
                        .setSmallIcon(R.drawable.ic_baseline_my_location_24)
                        .setContentTitle("Simple Notification...")
                        .setContentText("This is Simple Notification...")
                        .setPriority(Notification.PRIORITY_HIGH)

                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mNotificationChannel)
                notificationManager.notify(0, notificationBuilder.build())
            } else {
                val notificationBuilder2: NotificationCompat.Builder =
                    NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_baseline_my_location_24)
                        .setContentTitle("Simple Notification...")
                        .setContentText("This is Simple Notification...")


                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(0, notificationBuilder2.build())
            }

        }

        ////////Big Text Style Notification////////

        val BigTextNotificationButton = findViewById<Button>(R.id.big_text_notification)

        BigTextNotificationButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val imp = NotificationManager.IMPORTANCE_HIGH
                val mNotificationChannel =
                    NotificationChannel(CHANNEL_ID_ANDROID, CHANNEL_NAME, imp)

                val ic =
                    BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_my_location_24)

                val bigTextNotification: Notification.BigTextStyle = Notification.BigTextStyle()
                bigTextNotification.bigText(
                    "This is a demo of BIgTextStyle Notification this text notification has to be long in order to see the full effects of BigTextStyle Notification, It has three section under notification content" +
                            " title which is 'Big Text Notification', then actual place for big text below content title and at last summary text which shows the author of the text"
                )
                bigTextNotification.setBigContentTitle("Big Notification Title")
                bigTextNotification.setSummaryText("By: Emon Hossain")

                val notificationBuilder: Notification.Builder =
                    Notification.Builder(this, CHANNEL_ID_ANDROID)
                        .setSmallIcon(R.drawable.ic_baseline_my_location_24)
                        .setLargeIcon(ic)
                        .setStyle(bigTextNotification)
                        .setContentTitle("Simple Notification...")
                        .setContentText("This is Simple Notification...")
                        .setPriority(Notification.PRIORITY_DEFAULT)

                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mNotificationChannel)
                notificationManager.notify(0, notificationBuilder.build())

            } else {

                val ic =
                    BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_my_location_24)

                val bigTextNotification: NotificationCompat.BigTextStyle =
                    NotificationCompat.BigTextStyle()
                bigTextNotification.bigText(
                    "This is a demo of BIgTextStyle Notification this text notification has to be long in order to see the full effects of BigTextStyle Notification, It has three section under notification content" +
                            " title which is 'Big Text Notification', then actual place for big text below content title and at last summary text which shows the author of the text"
                )
                bigTextNotification.setBigContentTitle("Big Notification Title")
                bigTextNotification.setSummaryText("By: Emon Hossain")


                val notificationBuilder: NotificationCompat.Builder =
                    NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_baseline_my_location_24)
                        .setLargeIcon(ic)
                        .setStyle(bigTextNotification)
                        .setContentTitle("Big Text Notification...")
                        .setContentText("This is Big Text Notification...")

                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(0, notificationBuilder.build())
            }
        }

        ////////Big Picture Style Notification///////

        val BigPictureNotificationButton = findViewById<Button>(R.id.big_picture_notifications)

        BigPictureNotificationButton.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationBuilder: Notification.Builder? = null

                val imp = NotificationManager.IMPORTANCE_HIGH
                val mNotificationChannel =
                    NotificationChannel(CHANNEL_ID_ANDROID, CHANNEL_NAME, imp)

                val bitmap =
                    BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_my_location_24)
                val intent = Intent(this, BigPictureNotification::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(
                    this,
                    Calendar.getInstance().timeInMillis.toInt(),
                    intent,
                    0
                )

                notificationBuilder = Notification.Builder(this, CHANNEL_ID_ANDROID)
                    .setSmallIcon(R.drawable.ic_baseline_my_location_24)
                    .setStyle(Notification.BigPictureStyle(notificationBuilder).bigPicture(bitmap))
                    .setContentTitle("Big Picture Notification...")
                    .setContentText("This is Big Picture Notification...")
                    .addAction(
                        R.drawable.ic_baseline_my_location_24,
                        "Show Activity",
                        pendingIntent
                    )

                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mNotificationChannel)
                notificationManager.notify(0, notificationBuilder.build())

            } else {

                val bitmap =
                    BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_my_location_24)

                val bigPictureNotification: NotificationCompat.BigPictureStyle =
                    NotificationCompat.BigPictureStyle()
                bigPictureNotification.bigPicture(bitmap).build()

                val intent = Intent(this, BigPictureNotification::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(
                    this,
                    Calendar.getInstance().timeInMillis.toInt(),
                    intent,
                    0
                )

                val notificationBuilder: NotificationCompat.Builder =
                    NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_baseline_my_location_24)
                        .setStyle(bigPictureNotification)
                        .setContentTitle("Big Picture Notification...")
                        .setContentText("This is Big Picture Notification...")
                        .addAction(
                            R.drawable.ic_baseline_my_location_24,
                            "Show Activity",
                            pendingIntent
                        )

                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(0, notificationBuilder.build())
            }
        }

        ////////Inbox Style Notification////////

        val InboxStyleNotificationButton = findViewById<Button>(R.id.inbox_style_notification)

        InboxStyleNotificationButton.setOnClickListener {

            val builder = NotificationCompat.Builder(this)
            builder.setSmallIcon(R.drawable.ic_baseline_my_location_24)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.ic_baseline_my_location_24
                    )
                )
                .setStyle(
                    NotificationCompat.InboxStyle().addLine("Hi...").addLine("Are You There?")
                        .addLine("Its Me Emon!").setBigContentTitle("3 new messages from Emon")
                        .setSummaryText("Inbox")
                )
                .setAutoCancel(true)

            val path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            builder.setSound(path)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelid = "NOTIFICATION_CHANNEL_ID"
                val channel =
                    NotificationChannel(channelid, "Title", NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
                builder.setChannelId(channelid)
            }

            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }


        ////////Action Notification////////

        val ActionNotificationButton = findViewById<Button>(R.id.action_notification)

        ActionNotificationButton.setOnClickListener {
            val builder = NotificationCompat.Builder(this, ACTION_CHANNEL_ID)
            builder.setSmallIcon(R.drawable.ic_baseline_my_location_24)
                .setContentTitle("Action Buttons")
                .setAutoCancel(true)
                .setStyle(NotificationCompat.BigTextStyle().bigText("Click to visit google!"))
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            builder.addAction(android.R.drawable.ic_menu_view, "View", pendingIntent)

            val path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            builder.setSound(path)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    ACTION_CHANNEL_ID,
                    "Title",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
                builder.setChannelId(ACTION_CHANNEL_ID)
            }

            notificationManager.notify(ACTION_NOTIFICATION_ID, builder.build())
        }

        //////////Call Notification////////////

        val CallNotificationButton = findViewById<Button>(R.id.call_notification)

        CallNotificationButton.setOnClickListener {

            val notifyIntent = Intent(this, BigPictureNotification::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val sound = Uri.parse(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE).toString())

            val builder = NotificationCompat.Builder(this, ACTION_CHANNEL_ID)
            builder.setSmallIcon(R.drawable.ic_call)
                .setContentTitle("Incoming Call...")
                .setContentText("Nathaniel Rathban")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(notifyPendingIntent)
                .addAction(R.drawable.ic_call, "Answer", notifyPendingIntent)
                .addAction(R.drawable.ic_call_two, "Reject", notifyPendingIntent)
                .setTimeoutAfter(20000)
                .setAutoCancel(true)

            builder.setSound(sound)


            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val attributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE).build()

                val channel = NotificationChannel(ACTION_CHANNEL_ID, "Calling Notification Channel", NotificationManager.IMPORTANCE_HIGH)

                channel.enableLights(true)
                channel.enableVibration(true)
                channel.setSound(sound, attributes)

                notificationManager.createNotificationChannel(channel)
                builder.setChannelId(ACTION_CHANNEL_ID)
            }

            notificationManager.notify(ACTION_NOTIFICATION_ID, builder.build())

        }

    }
}