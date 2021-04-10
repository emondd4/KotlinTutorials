package com.emon.fakecallapp

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val FakeCall = findViewById<Button>(R.id.fake_call)
        val FakeSms = findViewById<Button>(R.id.fake_sms)

        FakeCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }

        FakeSms.setOnClickListener {
            val intent = Intent(this, SmsActivity::class.java)
            startActivity(intent)
        }

    }
}