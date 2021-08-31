package com.example.mvvmcoroutinesretrofitviewmodel.Retrofit

import com.example.mvvmcoroutinesretrofitviewmodel.Models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    suspend fun getDataFromApi(
        @Query("q") query: String
    ): RecyclerList
}