package com.bdtask.kotlinvoiceservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class TextToSpeechActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var text : EditText
    private lateinit var button: Button
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech)

        init();
    }

    private fun init() {
        text = findViewById(R.id.text_to_speech_EditText)
        button = findViewById(R.id.text_to_speech_Button)
        textToSpeech = TextToSpeech(this,this)

        button.setOnClickListener {
            textToSpeechFunction()
        }
    }

    private fun textToSpeechFunction() {
        val temp = text.text.toString()
        textToSpeech.speak(temp,TextToSpeech.QUEUE_FLUSH, null)
        Toast.makeText(this,temp,Toast.LENGTH_SHORT).show()
    }



    override fun onDestroy() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val res: Int = textToSpeech.setLanguage(Locale.US)
            if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"This language is not supported",Toast.LENGTH_SHORT).show()
            }else{
                button.isEnabled = true
            }
        }else{
            Toast.makeText(this,"Failed to initialize",Toast.LENGTH_SHORT).show()
        }

    }

}