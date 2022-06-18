package com.projectx.roomexternaldbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectx.roomexternaldbtest.Adapter.NoteAdapter
import com.projectx.roomexternaldbtest.Model.NoteModel
import com.projectx.roomexternaldbtest.ViewModel.ShowAllActivityViewModel
import kotlinx.android.synthetic.main.activity_show_all.*

class ShowAllActivity : AppCompatActivity() {

    lateinit var noteAdapter: NoteAdapter
    lateinit var viewModel: ShowAllActivityViewModel
    lateinit var noteList: List<NoteModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)

        viewModel = ViewModelProvider(this)[ShowAllActivityViewModel::class.java]

        viewModel.readAllNote.observe(this){
            recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            noteAdapter = NoteAdapter(it)
            recycler.adapter = noteAdapter
        }

    }
}