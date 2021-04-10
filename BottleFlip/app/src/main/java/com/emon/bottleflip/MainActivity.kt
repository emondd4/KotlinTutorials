package com.emon.bottleflip

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private var bottle: ImageView? = null
    private val random: Random = Random()
    private var lastDir = 0
    private var spinning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottle = findViewById(R.id.bottle);

    }

    fun spinBottle(view: View) {

        if (!spinning) {
            val newDir = random.nextInt(1800)
            val pivotX = (bottle!!.width / 2).toFloat()
            val pivotY = (bottle!!.height / 2).toFloat()
            val rotate: Animation = RotateAnimation(lastDir.toFloat(), newDir.toFloat(), pivotX, pivotY)
            rotate.duration = 2500
            rotate.fillAfter = true
            rotate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    spinning = true
                }

                override fun onAnimationEnd(animation: Animation?) {
                    spinning = false
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
            lastDir = newDir
            bottle!!.startAnimation(rotate)
        }

    }
}