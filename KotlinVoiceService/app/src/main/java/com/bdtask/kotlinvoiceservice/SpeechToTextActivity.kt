package com.bdtask.kotlinvoiceservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class SpeechToTextActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var button: Button
    private val REQ_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_to_text)

        init()
    }

    private fun init() {
        text = findViewById(R.id.speech_to_text_TextView)
        button = findViewById(R.id.speech_to_text_Button)

        button.setOnClickListener {
            SpeechFunction()
        }
    }

    private fun SpeechFunction() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something...")

        startActivityForResult(intent,REQ_INPUT)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_INPUT){
            if (resultCode == RESULT_OK || data != null){
                val res: ArrayList<String> = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                text.text = res[0]
            }
        }
    }
}