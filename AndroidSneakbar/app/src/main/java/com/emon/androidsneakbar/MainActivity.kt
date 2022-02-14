package com.emon.androidsneakbar

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {

            val contextView = findViewById<View>(android.R.id.content)
            Snackbar.make(contextView, "No Internet Connection", Snackbar.LENGTH_SHORT)
                .setAction("Try Again") {

            }.show()

        }

    }
}