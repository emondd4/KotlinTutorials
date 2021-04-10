package com.emon.fakecallapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class SmsActivity : AppCompatActivity() {

    lateinit var Spinner: Spinner
    lateinit var Name: EditText
    lateinit var Phone: EditText
    lateinit var Message: EditText
    lateinit var Send: Button
    private val NOTIFICATION_ID = 2
    private var mili: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        Spinner = findViewById(R.id.TimeSpinnerSMS)
        Name = findViewById(R.id.SmsNameET)
        Phone = findViewById(R.id.SmsPhone_ET)
        Message = findViewById(R.id.SmsMessage_ET)
        Send = findViewById(R.id.SmsButton)

        Send.setOnClickListener {


            val spinner: String = Spinner.selectedItem.toString()

            if (spinner == "1 min"){
                mili = 60000
            }else if (spinner == "2 min"){
                mili = 120000
            }else if (spinner == "5 min"){
                mili = 300000
            }else if (spinner == "10 min"){
                mili = 600000
            }
            else if (spinner == "15 min"){
                mili = 900000
            }

            Handler(Looper.getMainLooper()).postDelayed({

                val name: String = Name.text.toString()
                val phone: String = Phone.text.toString()
                val message: String = Message.text.toString()

                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_APP_MESSAGING)
                val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

                val builder = NotificationCompat.Builder(this)
                builder.setSmallIcon(R.drawable.ic_message)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_message))
                    .setStyle(
                        NotificationCompat.InboxStyle().addLine("From: $name")
                            .addLine("Number: $phone").addLine(
                                message
                            ).setBigContentTitle("Unread Messages").setSummaryText("Inbox")
                    )
                    .setAutoCancel(true)
                    .addAction(R.drawable.ic_message, "Go To Inbox", pendingIntent)

                val path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                builder.setSound(path)

                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channelid = "NOTIFICATION_CHANNEL_ID"
                    val channel = NotificationChannel(
                        channelid,
                        "Messageing Notification Channel",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                    builder.setChannelId(channelid)
                }

                notificationManager.notify(NOTIFICATION_ID, builder.build())

            }, mili)

            this.moveTaskToBack(true)

        }

    }
}