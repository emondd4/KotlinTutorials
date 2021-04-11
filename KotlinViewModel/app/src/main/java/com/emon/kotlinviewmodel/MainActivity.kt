package com.emon.kotlinviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        textView.text = viewModel.number.toString()

        button.setOnClickListener {

            viewModel.addNumber()
            textView.text = viewModel.number.toString()

        }

    }
}