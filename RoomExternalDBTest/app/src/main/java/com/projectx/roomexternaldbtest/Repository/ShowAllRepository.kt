package com.projectx.roomexternaldbtest.Repository

import androidx.lifecycle.LiveData
import com.projectx.roomexternaldbtest.Model.NoteModel
import com.projectx.roomexternaldbtest.Room.NoteDao

class ShowAllRepository(private val noteDao: NoteDao) {

    val readAllNote: LiveData<List<NoteModel>> = noteDao.getAllNotes()

    suspend fun saveNote(noteModel: NoteModel){
        noteDao.addNote(noteModel)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }


}