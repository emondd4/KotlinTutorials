package com.emon.CoroutinesRetrofitViewModel.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.emon.CoroutinesRetrofitViewModel.DataRequestInterface
import com.emon.CoroutinesRetrofitViewModel.ModelClass.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import kotlin.coroutines.coroutineContext

class BestSellingViewModel(application: Application) : AndroidViewModel(application) {

    val products: LiveData<ArrayList<ProductData?>> = MutableLiveData()

    init {

        viewModelScope.launch {
            products as MutableLiveData
            products.value = getProductInfo()
        }
    }

    private suspend fun getProductInfo(): ArrayList<ProductData?>? {

        val response = DataRequestInterface(getApplication<Application>().applicationContext).getRandomProducts(
            "harunApp",
            "250",
            "")

        return withContext(Dispatchers.IO){
            response?.await()?.data?.random_product_list
        }
    }
}