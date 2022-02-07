package com.emon.mvvmapicallfinal.Retrofit

import com.emon.mvvmapicallfinal.Model.BaseResponse
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): BaseResponse
}