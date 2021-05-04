package com.emon.languagechangingwithoutrecreate

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var text: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.changeText)
        button = findViewById(R.id.changeBtn)

        button.setOnClickListener {

            val current = resources.configuration.locale
            println(current)

            if (current.toString() == "fr"){
                setLocale("en")
            }else{
                setLocale("fr")
            }
        }

    }

    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        onConfigurationChanged(conf)
    }

    private fun setLocale2(lang: String, s: String) {
        val myLocale = Locale(lang,s)
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        onConfigurationChanged(conf)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {


        val current = resources.configuration.locale

        if (current.equals("fr")){
            text.setText(R.string.hello_world)
            button.setText(R.string.change_language)
        }else{
            text.setText(R.string.hello_world)
            button.setText(R.string.change_language)
        }

        super.onConfigurationChanged(newConfig)

    }
}