package com.projectx.roomexternaldbtest.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.projectx.roomexternaldbtest.Repository.ShowAllRepository
import com.projectx.roomexternaldbtest.Model.NoteModel
import com.projectx.roomexternaldbtest.Room.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowAllActivityViewModel(application: Application): AndroidViewModel(application) {

    val readAllNote: LiveData<List<NoteModel>>

    private val repository: ShowAllRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = ShowAllRepository(noteDao)
        readAllNote = repository.readAllNote
    }

    fun addNote(noteModel: NoteModel){
        viewModelScope.launch (Dispatchers.IO) {
            repository.saveNote(noteModel)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }

}