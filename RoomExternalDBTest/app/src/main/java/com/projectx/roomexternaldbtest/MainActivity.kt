package com.projectx.roomexternaldbtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.projectx.roomexternaldbtest.Model.NoteModel
import com.projectx.roomexternaldbtest.Repository.ShowAllRepository
import com.projectx.roomexternaldbtest.Room.NoteDao
import com.projectx.roomexternaldbtest.ViewModel.ShowAllActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ShowAllActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ShowAllActivityViewModel::class.java]

        saveBtn.setOnClickListener {

            val title = titleET.text
            val description = desET.text

            if (title.isEmpty()){
                titleET.error = "Can't Be Empty"
                return@setOnClickListener
            }

            if (description.isEmpty()){
                desET.error = "Can't Be Empty"
                return@setOnClickListener
            }

            viewModel.addNote(noteModel = NoteModel(
                id = 0,
                title = title.toString(),
                description = description.toString()
            ))
            titleET.text.clear()
            desET.text.clear()

            Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
        }

        showBtn.setOnClickListener {
            startActivity(Intent(this,ShowAllActivity::class.java))
        }

    }
}