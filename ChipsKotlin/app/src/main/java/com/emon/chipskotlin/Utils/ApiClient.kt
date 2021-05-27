package com.emon.chipskotlin.Utils

import android.content.Context
import com.emon.chipskotlin.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiClient {

    companion object {


        fun getRetrofit(context: Context): Retrofit {
            return Retrofit.Builder()
                .baseUrl(context.resources?.getString(R.string.base_url)!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getRetrofitLong(context: Context): Retrofit? {

            val httpClient = OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build()

            return Retrofit.Builder()
                .client(httpClient)
                .baseUrl(context.resources?.getString(R.string.base_url)!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}