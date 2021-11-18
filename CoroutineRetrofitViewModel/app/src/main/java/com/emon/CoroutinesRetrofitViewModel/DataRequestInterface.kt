package com.emon.CoroutinesRetrofitViewModel

import android.content.Context
import com.emon.CoroutinesRetrofitViewModel.ModelClass.ProductBaseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    ) : Call<ProductBaseModel?>?

    companion object {

        operator fun invoke(applicationContext: Context): DataRequestInterface {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(applicationContext.resources.getString(R.string.base_url))
                .build()
                .create(DataRequestInterface::class.java)
        }
    }

}
