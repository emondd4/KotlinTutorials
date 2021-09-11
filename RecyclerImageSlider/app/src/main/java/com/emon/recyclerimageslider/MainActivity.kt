package com.emon.recyclerimageslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.emon.recyclerimageslider.Fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.homeFrameLayout,HomeFragment(),"HomeFragment").disallowAddToBackStack().commit()
        }

    }
}