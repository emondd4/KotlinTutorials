package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView1.text = "This is textView 1"
        binding.textView2.text = "This is textView 2"

        binding.buttonOpenFragment.setOnClickListener {
         openFragment()
        }

    }

    private fun openFragment() {
        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,ExampleFragment()
        ).addToBackStack(null).commit()
    }
}