package com.emon.roomcrud.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emon.roomcrud.Model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Query("Select * FROM user_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<User>>

}