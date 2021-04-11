package com.emon.roomcrud.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.emon.roomcrud.Model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("Select * FROM user_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<User>>

}