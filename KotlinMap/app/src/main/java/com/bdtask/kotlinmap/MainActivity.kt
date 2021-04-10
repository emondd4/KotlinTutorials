package com.bdtask.kotlinmap

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val PACKAGE_NAME:String = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit_text = findViewById<EditText>(R.id.edit_text)
        val search_button = findViewById<Button>(R.id.search_button)

        search_button.setOnClickListener {
            val temp: String = edit_text.text.toString()
            val app_installed: Boolean

            val uriIntent = Uri.parse("geo:0,0?q=${edit_text.text}")
            val MapIntent = Intent(Intent.ACTION_VIEW, uriIntent)
            MapIntent.setPackage(PACKAGE_NAME)

            val pm = packageManager
            app_installed = try {
                pm.getPackageInfo("com.google.android.apps.maps", PackageManager.GET_ACTIVITIES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }

            if (app_installed){
                startActivity(MapIntent)
            }else{
                Toast.makeText(this, "Map is not installed in your device", Toast.LENGTH_SHORT).show()
            }

        }
    }


}