package com.emon.chitro.Retrofit


import com.emon.chitro.ModelClass.TotalResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface DataRequestInterface {

    @GET("api/")
    fun getPosts(@QueryMap params: Map<String,String> ) : Call<TotalResponse?>?
}