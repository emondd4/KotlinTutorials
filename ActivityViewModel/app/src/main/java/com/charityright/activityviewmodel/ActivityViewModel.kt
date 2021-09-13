package com.charityright.activityviewmodel

import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel

class ActivityViewModel: ViewModel() {

    lateinit var layout: LinearLayout

    fun initView(v: LinearLayout){
        layout = v
    }

    fun hideView(){
        layout.visibility = View.GONE
    }

    fun showView(){
        layout.visibility = View.VISIBLE
    }

}