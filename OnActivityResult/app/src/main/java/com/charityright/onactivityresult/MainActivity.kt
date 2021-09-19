package com.charityright.onactivityresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var startActivity: Button

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            resultText.text = it.data?.extras?.get("name").toString()
        }else{
            resultText.text = "Result Canceled"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.textView)
        startActivity = findViewById(R.id.button)

        startActivity.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            resultLauncher.launch(intent)
        }

    }
}