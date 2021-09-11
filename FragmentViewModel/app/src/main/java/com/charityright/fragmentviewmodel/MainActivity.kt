package com.charityright.fragmentviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.charityright.fragmentviewmodel.Fragments.HomeFragment
import androidx.lifecycle.ViewModelProvider




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.HomeFrameLayout,HomeFragment(),"HomeFragment").disallowAddToBackStack().commit()
        }

    }
}