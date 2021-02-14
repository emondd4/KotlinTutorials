package com.emon.staticbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class ExampleBroadcastReveiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
                Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
            }
        }
        if (intent != null) {
            if (Intent.ACTION_POWER_CONNECTED == intent.action) {
                Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
            }
        }
        if (intent != null) {
            if (Intent.ACTION_POWER_DISCONNECTED == intent.action) {
                Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
            }
        }
        if (intent != null) {
            if (Intent.ACTION_HEADSET_PLUG == intent.action) {
                Toast.makeText(context, "Headset plugged", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
