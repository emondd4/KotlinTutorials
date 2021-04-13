package com.emon.offlinecachingwithroom.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.emon.offlinecachingwithroom.Room.Model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

    @Query("Select * FROM product_table ORDER BY product_id ASC")
    fun getAllProducts(): LiveData<List<Product>>

}