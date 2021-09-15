package com.emon.mvvmretrofit.Repository

import com.emon.mvvmretrofit.Model.RandomProductsModel.RandomProductBaseModel
import com.emon.mvvmretrofit.Retrofit.DataRequestInterface
import retrofit2.Call

class ProductRepository(private val dataRequestInterface: DataRequestInterface) {

    val randomProductsData: Call<RandomProductBaseModel?>? = dataRequestInterface.getRandomProducts(
        "harunApp","20","1")
}