package com.bdtask.kotlinvoiceservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TextToSpeech = findViewById<Button>(R.id.text_to_speech)
        val SpeechToText = findViewById<Button>(R.id.speech_to_text)

        TextToSpeech.setOnClickListener {
            startActivity(Intent(this,TextToSpeechActivity::class.java))
        }

        SpeechToText.setOnClickListener {
            startActivity(Intent(this,SpeechToTextActivity::class.java))
        }

    }
}