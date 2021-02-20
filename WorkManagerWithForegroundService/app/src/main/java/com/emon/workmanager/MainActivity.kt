package com.emon.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun StartService(view: View) {

        val serviceIntent = Intent(this, ExampleService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)

    }

    fun StopService(view: View) {


        Handler(Looper.getMainLooper()).postDelayed({

            val serviceIntent = Intent(this, ExampleService::class.java)
            stopService(serviceIntent)

        },10000)

    }
}