package com.charityright.expandablerecycleritem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var customRecycler: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customRecycler = findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = Adapter()
        customRecycler.layoutManager = layoutManager
        customRecycler.adapter = adapter

    }
}