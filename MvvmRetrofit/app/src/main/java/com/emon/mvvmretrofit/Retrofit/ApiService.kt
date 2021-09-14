package com.emon.mvvmretrofit.Retrofit

import android.content.Context
import com.emon.mvvmretrofit.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiService {

    companion object {
        fun getRetrofit(context: Context): Retrofit {
            return Retrofit.Builder()
                .baseUrl(context.resources?.getString(R.string.base_url)!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}