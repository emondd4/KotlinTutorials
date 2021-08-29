package com.emon.CoroutinesRetrofitViewModel.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emon.CoroutinesRetrofitViewModel.DataRequestInterface
import com.emon.CoroutinesRetrofitViewModel.ModelClass.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class BestSellingViewModel : ViewModel() {

    val products: LiveData<ArrayList<ProductData?>> = MutableLiveData()

    init {

        viewModelScope.launch {
            products as MutableLiveData
            products.value = getProductInfo()
        }
    }

    private suspend fun getProductInfo(): ArrayList<ProductData?>? {

        val response = DataRequestInterface().getRandomProducts(
            "harunApp",
            "250",
            "")

        return withContext(Dispatchers.IO){
            response?.await()?.data?.random_product_list
        }
    }
}