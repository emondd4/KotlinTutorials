package com.emon.embededroom.Repository

import androidx.lifecycle.LiveData
import com.emon.embededroom.Model.Person
import com.emon.embededroom.Room.MyDao

class MyRepository(private val myDao: MyDao) {

    val readPerson: LiveData<List<Person>> = myDao.readPerson()

    suspend fun insertPerson(person: Person){
        myDao.insertPerson(person)
    }

}