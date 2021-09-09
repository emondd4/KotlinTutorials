package com.charityright.customspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.util.*



class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)

        val testArray = resources.getStringArray(R.array.dummy)


        val spinnerArrayAdapter = ArrayAdapter(this, R.layout.spinner_item,R.id.textItem, testArray)
        spinner.adapter = spinnerArrayAdapter

    }
}