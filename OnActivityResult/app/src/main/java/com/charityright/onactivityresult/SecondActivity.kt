package com.charityright.onactivityresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : AppCompatActivity() {

    private lateinit var data: EditText
    private lateinit var finish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        data = findViewById(R.id.editText)
        finish = findViewById(R.id.button2)

        finish.setOnClickListener {
            val tempData = data.text.toString()

            if (tempData.isNotEmpty()){
                val intent = Intent()
                intent.putExtra("name",tempData)
                setResult(RESULT_OK,intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
    }
}