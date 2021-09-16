package com.charityright.randomimageslide


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.os.Handler;


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        val imageList = arrayOf(
            "https://images.unsplash.com/photo-1521676259650-675b5bfec1ae?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80",
            "https://images.unsplash.com/photo-1552234994-66ba234fd567?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80",
            "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80"
        )

        changeImage(imageList)



        val handler = Handler()

        val runnableName: Runnable = object : Runnable {
            override fun run() {
                changeImage(imageList)
                handler.postDelayed(this, 3000)
            }
        }

        handler.post(runnableName)

    }

    private fun changeImage(imageList: Array<String>) {
        val randomNumber: Int = (0..2).random()

        when (randomNumber){
            0 -> {
                Picasso.get().load(imageList[0]).fit().into(imageView)
            }

            1 -> {
                Picasso.get().load(imageList[1]).fit().into(imageView)
            }

            2 -> {
                Picasso.get().load(imageList[2]).fit().into(imageView)
            }
        }
    }
}