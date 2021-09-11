package com.charityright.fragmentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData


class FragmentViewModel : ViewModel() {
    private val messageContainerA = MutableLiveData<String>()

    fun sendMessageToA(msg: String) {
        messageContainerA.value = msg
    }

    fun getMessageContainerA(): LiveData<String> {
        return messageContainerA
    }

}