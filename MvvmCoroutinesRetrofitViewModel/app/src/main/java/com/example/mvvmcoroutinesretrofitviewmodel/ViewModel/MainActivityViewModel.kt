package com.example.mvvmcoroutinesretrofitviewmodel.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcoroutinesretrofitviewmodel.Models.RecyclerList
import com.example.mvvmcoroutinesretrofitviewmodel.Retrofit.RetrofitInstance
import com.example.mvvmcoroutinesretrofitviewmodel.Retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    var recyclerListLiveData: MutableLiveData<RecyclerList> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList>{
        return  recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
            val response = retrofitInstance.getDataFromApi("ny")
            recyclerListLiveData.postValue(response)
        }
    }
}