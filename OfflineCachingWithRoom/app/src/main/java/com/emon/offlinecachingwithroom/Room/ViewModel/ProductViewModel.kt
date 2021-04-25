package com.emon.offlinecachingwithroom.Room.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.emon.offlinecachingwithroom.Room.Model.Product
import com.emon.offlinecachingwithroom.Room.ProductDatabase
import com.emon.offlinecachingwithroom.Room.Repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Product>>
    private val repository: ProductRepository

    init {

        val productDao = ProductDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        readAllData = repository.readAllData

    }

    fun addProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProduct(product)
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
        }
    }

    fun deleteAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllProducts()
        }
    }

}