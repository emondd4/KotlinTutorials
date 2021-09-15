package com.emon.mvvmretrofit.Retrofit

import com.emon.mvvmretrofit.Model.RandomProductsModel.RandomProductBaseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DataRequestInterface {

    @FormUrlEncoded
    @POST("api/limarket_api/random_product_list")
    fun getRandomProducts(
        @Field("api_key") key: String,
        @Field("per_page") per_page: String,
        @Field("page") page: String
    ) : Call<RandomProductBaseModel?>?

}