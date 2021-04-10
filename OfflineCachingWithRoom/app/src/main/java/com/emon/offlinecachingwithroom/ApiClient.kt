package com.emon.offlinecachingwithroom

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClient {

    companion object{

       fun getRetrofit(context: Context): Retrofit {
           return Retrofit.Builder()
               .baseUrl(context.resources?.getString(R.string.base_url)!!)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
        }
    }
}