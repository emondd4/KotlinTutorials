package com.emon.offlinecachingwithroom

import com.bdtask.limarket.ModelClass.ProductModel.ProductModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DataRequestInterface {

    @FormUrlEncoded
    @POST("api/limarket_api/product_list")
    fun getProductList(@Field("api_key") api_key:String, @Field("per_page") per_page:String, @Field("page") page:Int, @Field("product_status") product_status:String,
                       @Field("product_id") product_id: String, @Field("seller_id") seller_id: String, @Field("category_id") category_id: String,
                       @Field("price_start") price_start: String, @Field("price_end") price_end: String) : Call<ProductModel?>?

}