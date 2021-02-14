package com.emon.kotlincustombroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast




class ExampleBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent != null){

            if ("com.codinginflow.EXAMPLE_ACTION" == intent.action) {
                val receivedText = intent.getStringExtra("com.codinginflow.EXTRA_TEXT")
                Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show()
            }
        }
    }
}