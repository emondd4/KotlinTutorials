package com.example.coinflip

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var coin: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coin = findViewById(R.id.imageView)

        coin.setOnClickListener {
            val randomNumber: Int = (1..2).random()

            if (randomNumber == 1){
                flipCoin(R.drawable.ic_head,"Heads")
            }else{
                flipCoin(R.drawable.ic_tail,"Tails")
            }
        }

    }

    private fun flipCoin(imageId: Int, coinSide: String) {
        coin.animate().apply {
            duration = 1000
            rotationYBy(1800f)
            coin.isClickable = false
        }.withEndAction {
            coin.setImageResource(imageId)
            Toast.makeText(this@MainActivity,coinSide,Toast.LENGTH_SHORT).show()
            coin.isClickable = true
        }.start()
    }

}