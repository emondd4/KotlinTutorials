package com.emon.explisitbroadcastsender

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)

    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sendBroadcast(view: View) {

        val intent = Intent("com.codinginflow.EXAMPLE_ACTION")
        //intent.setClass(this, ExampleBroadcastReceiver2.class);
        /*ComponentName cn = new ComponentName("com.codinginflow.broadcastexample",
                "com.codinginflow.broadcastexample.ExampleBroadcastReceiver");
        intent.setComponent(cn);*/
        /*intent.setClassName("com.codinginflow.broadcastexample",
                "com.codinginflow.broadcastexample.ExampleBroadcastReceiver");*/
        //intent.setPackage("com.codinginflow.broadcastexample");
        //intent.setClass(this, ExampleBroadcastReceiver2.class);
        /*ComponentName cn = new ComponentName("com.codinginflow.broadcastexample",
                "com.codinginflow.broadcastexample.ExampleBroadcastReceiver");
        intent.setComponent(cn);*/
        /*intent.setClassName("com.codinginflow.broadcastexample",
                "com.codinginflow.broadcastexample.ExampleBroadcastReceiver");*/
        //intent.setPackage("com.codinginflow.broadcastexample");
        val packageManager = packageManager
        val infos = packageManager.queryBroadcastReceivers(intent, 0)
        for (info in infos) {
            val cn = ComponentName(info.activityInfo.packageName,
                    info.activityInfo.name)
            intent.component = cn
            sendBroadcast(intent)
        }
        //sendBroadcast(intent);

    }
}