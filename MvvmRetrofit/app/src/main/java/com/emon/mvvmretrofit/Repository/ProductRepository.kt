package com.emon.mvvmretrofit.Repository

import android.app.Application
import com.emon.mvvmretrofit.Model.RandomProductsModel.RandomProductBaseModel
import com.emon.mvvmretrofit.R
import com.emon.mvvmretrofit.Retrofit.DataRequestInterface
import retrofit2.Call

class ProductRepository(
    private val dataRequestInterface: DataRequestInterface,
    private val application: Application
) {

    val randomProductsData: Call<RandomProductBaseModel?>? = dataRequestInterface.getRandomProducts(
        application.resources.getString(R.string.key),"20","1")
}