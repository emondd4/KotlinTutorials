package com.emon.offlinecachingwithroom.Room.Repository

import androidx.lifecycle.LiveData
import com.emon.offlinecachingwithroom.Room.Model.Product
import com.emon.offlinecachingwithroom.Room.ProductDao

class ProductRepository(private val productDao: ProductDao) {

    val readAllData: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    suspend fun deleteAllProducts(){
        productDao.deleteAllProducts()
    }

    suspend fun deleteProduct(product: Product){
        productDao.deleteProduct(product)
    }

}