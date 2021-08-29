package com.example.retrofitcoroutinesexample

import com.example.retrofitcoroutinesexample.Model.CommentsModelItem
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("/comments")
    fun getComments(): Call<List<CommentsModelItem>>

}