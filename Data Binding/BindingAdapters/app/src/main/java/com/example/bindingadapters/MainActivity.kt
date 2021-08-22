package com.example.bindingadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bindingadapters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = DataModel("Emon","Android Developer","https://images.unsplash.com/photo-1629489420539-f28128455bae?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1234&q=80",11)
        binding.dataModel = data
    }
}