package com.emon.fakecallapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.*
import android.telephony.TelephonyManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FakeCallingActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private var name: String = ""
    private var number: String = ""
    private var networkCarrier = ""
    private var second = 0

    lateinit var Name : TextView
    lateinit var Number : TextView
    lateinit var Operator : TextView
    lateinit var CallTime : TextView


    lateinit var vibrator : Vibrator
    lateinit var ringTone : MediaPlayer
    lateinit var countDownTimer : CountDownTimer
    lateinit var handler: Handler
    lateinit var Receive : ImageView
    lateinit var Reject : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_calling)

        sharedPreferences = this.getSharedPreferences("Info", Context.MODE_PRIVATE)

        handler = Handler(Looper.getMainLooper())

        name = sharedPreferences.getString("name", null).toString()
        number = sharedPreferences.getString("number", null).toString()

        Name = findViewById(R.id.userName)
        Number = findViewById(R.id.userNumber)
        Operator = findViewById(R.id.userNetwork)
        CallTime = findViewById(R.id.callType)

        Receive = findViewById(R.id.receive_call)
        Reject = findViewById(R.id.reject_call)

        Name.text = name
        Number.text = number

        val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        networkCarrier = tm.networkOperatorName

        if (networkCarrier != null) {
            Operator.text = networkCarrier
        } else {
            Operator.text = "Operator Info Unavailable"
        }

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringTone = MediaPlayer.create(applicationContext, notification)
        ringTone.start()

        countDownTimer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                vibrator.vibrate(500)
            }

            override fun onFinish() {
                ringTone.stop()
                vibrator.cancel()
            }
        }.start()


        Reject.setOnClickListener {
            ringTone.stop()
            vibrator.cancel()
            finish()
        }

        Receive.setOnClickListener {

            Receive.visibility = View.GONE
            ringTone.stop()
            vibrator.cancel()
            countDownTimer.cancel()
            CallTime.text = "On Call"
        }

    }
}