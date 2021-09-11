package com.emon.recyclerimageslider.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {
    private val imagePath = MutableLiveData<String>()

    fun setImagePath(msg: String){
        imagePath.value = msg
    }

    fun getImagePath(): LiveData<String> {
        return imagePath
    }
}