package com.emon.coroutinesexample.ViewModel

import android.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emon.coroutinesexample.ApiClient
import com.emon.coroutinesexample.DataRequestInterface
import com.emon.coroutinesexample.ModelClass.ProductData
import com.emon.coroutinesexample.R
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BestSellingViewModel : ViewModel() {

    val products: LiveData<ArrayList<ProductData?>> = MutableLiveData()

    init {

        viewModelScope.launch {
            products as MutableLiveData
            products.value = getProductInfo()
        }
    }

    private suspend fun getProductInfo(): ArrayList<ProductData?> {

        val response = DataRequestInterface().getProductList(
            "harunApp",
            "235",
            1,
            "2",
            "",
            "",
            "",
            "",
            "")

        return withContext(Dispatchers.IO){
            response?.body()?.product_list!!
        }
    }
}