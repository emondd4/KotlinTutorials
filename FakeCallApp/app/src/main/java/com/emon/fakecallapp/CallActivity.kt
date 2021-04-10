package com.emon.fakecallapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class CallActivity : AppCompatActivity() {

    lateinit var CalllerName: EditText
    lateinit var CallerNumber: EditText
    lateinit var Spinner: Spinner
    var mili: Long = 0

    private val ACTION_NOTIFICATION_ID = 2
    private val ACTION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.call_activity)

        sharedPreferences = this.getSharedPreferences("Info", Context.MODE_PRIVATE)

        CalllerName = findViewById(R.id.callerNameET)
        CallerNumber = findViewById(R.id.callerNumET)
        Spinner = findViewById(R.id.TimeSpinner)


        val Call = findViewById<Button>(R.id.CallButton);
        Call.setOnClickListener {

            val spinner:String = Spinner.selectedItem.toString()

            if (spinner == "1 min"){
                mili = 20000
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

                val name: String = CalllerName.text.toString()
                val number: String = CallerNumber.text.toString()

                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putString("number",number)
                editor.apply()

                val notifyIntent = Intent(this, FakeCallingActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val notifyPendingIntent = PendingIntent.getActivity(
                    this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
                )

                val sound = Uri.parse(
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE).toString()
                )

                val builder = NotificationCompat.Builder(this, ACTION_CHANNEL_ID)
                builder.setSmallIcon(R.drawable.ic_call)
                    .setContentTitle(name)
                    .setContentText(number)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(notifyPendingIntent)
                    .addAction(R.drawable.ic_call, "Answer", notifyPendingIntent)
                    .addAction(R.drawable.ic_call_end, "Reject", notifyPendingIntent)
                    .setTimeoutAfter(20000)
                    .setAutoCancel(true)

                builder.setSound(sound)


                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    val attributes = AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE).build()

                    val channel = NotificationChannel(
                        ACTION_CHANNEL_ID,
                        "Calling Notification Channel",
                        NotificationManager.IMPORTANCE_HIGH
                    )

                    channel.enableLights(true)
                    channel.enableVibration(true)
                    channel.setSound(sound, attributes)

                    notificationManager.createNotificationChannel(channel)
                    builder.setChannelId(ACTION_CHANNEL_ID)
                }

                notificationManager.notify(ACTION_NOTIFICATION_ID, builder.build())

            }, mili)

            this.moveTaskToBack(true)

        }

    }
}