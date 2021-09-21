package com.charityright.viewexpandingwithanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView



class MainActivity : AppCompatActivity() {

    private lateinit var show: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show = findViewById(R.id.button)
        image = findViewById(R.id.imageView)

        show.setOnClickListener {
            hideView(image)
        }
    }

    private fun hideView(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_down)
        animation.duration = 1000;
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                view.visibility = View.VISIBLE
            }
        })
        view.startAnimation(animation)
    }
}