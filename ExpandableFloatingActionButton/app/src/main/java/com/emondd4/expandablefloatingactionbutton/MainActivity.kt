package com.emondd4.expandablefloatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }

    private lateinit var mainFloat: FloatingActionButton
    private lateinit var firstFloat: FloatingActionButton
    private lateinit var secondFloat: FloatingActionButton

    private var clicked : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFloat = findViewById(R.id.mainFloat)
        firstFloat = findViewById(R.id.firstFloat)
        secondFloat = findViewById(R.id.secondFloat)

        mainFloat.setOnClickListener {
            setVisibility(clicked)
            setAnimation(clicked)
            setClickable(clicked)
            clicked = !clicked
        }

        firstFloat.setOnClickListener {
            Toast.makeText(this,"First Float Button Clicked",Toast.LENGTH_SHORT).show()
        }

        secondFloat.setOnClickListener {
            Toast.makeText(this,"Second Float Button Clicked",Toast.LENGTH_SHORT).show()
        }

    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked){
            firstFloat.isClickable = true
            secondFloat.isClickable = true
        }else{
            firstFloat.isClickable = false
            secondFloat.isClickable = false
        }
    }

    private fun setAnimation(clicked: Boolean) {

        if (!clicked){
            firstFloat.visibility = View.VISIBLE
            secondFloat.visibility = View.VISIBLE
        }else{
            firstFloat.visibility = View.INVISIBLE
            secondFloat.visibility = View.INVISIBLE
        }

    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked){
            firstFloat.animation = fromBottom
            secondFloat.animation = fromBottom
            mainFloat.animation = rotateOpen
        }else{
            firstFloat.animation = toBottom
            secondFloat.animation = toBottom
            mainFloat.animation = rotateClose
        }
    }
}