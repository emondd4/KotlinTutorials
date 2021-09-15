package com.emon.mvvmretrofit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.emon.mvvmretrofit.Model.RandomProductsModel.RandomProductBaseModel
import com.emon.mvvmretrofit.Model.RandomProductsModel.RandomProductsData
import com.emon.mvvmretrofit.Repository.ProductRepository
import com.emon.mvvmretrofit.Retrofit.ApiService
import com.emon.mvvmretrofit.Retrofit.DataRequestInterface

import retrofit2.Call

class ProductViewModel(application: Application): AndroidViewModel(application) {
    val getRandomProducts: Call<RandomProductBaseModel?>

    private val repository: ProductRepository
    private var randomProductsData: MutableList<RandomProductsData?>? = null

    init {
        val dataRequestInterface = ApiService.getRetrofit(application)
            .create(DataRequestInterface::class.java)

        repository = ProductRepository(dataRequestInterface,application)
        getRandomProducts = repository.randomProductsData!!
    }

    fun setRandomProducts(randomProductsData: MutableList<RandomProductsData?>){
        this.randomProductsData = randomProductsData
    }

    fun getRandomProducts(): MutableList<RandomProductsData?>? {
        return randomProductsData
    }

}