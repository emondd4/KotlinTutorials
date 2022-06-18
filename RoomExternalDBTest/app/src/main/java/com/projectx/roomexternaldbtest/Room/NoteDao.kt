package com.projectx.roomexternaldbtest.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectx.roomexternaldbtest.Model.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteModel: NoteModel)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("Select * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteModel>>

}