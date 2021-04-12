package com.emon.embededroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emon.embededroom.Adapter.MyAdapter
import com.emon.embededroom.Model.Address
import com.emon.embededroom.Model.Person
import com.emon.embededroom.ViewModel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MyAdapter() }
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val address = Address("Sesame Street", 10)
        val person = Person(0, "John", "Doe", 25, address)
        myViewModel.insertPerson(person)

        myViewModel.readPerson.observe(this, {
            adapter.setData(it)
            it[0].address.streetNumber
        })

    }
}