package com.emon.kotlincustombroadcastreceiver

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var exampleBroadcastReceiver = ExampleBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter("com.codinginflow.EXAMPLE_ACTION")
        registerReceiver(exampleBroadcastReceiver, filter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(exampleBroadcastReceiver)
    }

}