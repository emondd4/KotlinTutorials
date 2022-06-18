package com.projectx.roomexternaldbtest.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.projectx.roomexternaldbtest.Model.NoteModel

@Database(entities = [NoteModel::class], version = 1,exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun  getDatabase(context: Context): NoteDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instanse = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database.db")
                    .createFromAsset("database/note_database.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instanse
                return  instanse
            }
        }

    }
}