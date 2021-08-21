package com.emon.databindingincludes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emon.databindingincludes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUser = User("Emon", "Hossain")

        binding.user = mUser
    }
}