package com.example.mvvmcoroutinesretrofitviewmodel.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object{
        val BaseURL = "https://api.github.com/search/"

        fun getRetroInstance(): Retrofit{
            return  Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}