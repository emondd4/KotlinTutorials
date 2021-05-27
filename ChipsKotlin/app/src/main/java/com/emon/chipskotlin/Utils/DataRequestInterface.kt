package com.emon.chipskotlin.Utils


import com.bdtask.limarket.ModelClass.SearchProductModel.SearchProductModel
import retrofit2.Call
import retrofit2.http.*


interface DataRequestInterface {

    @FormUrlEncoded
    @POST("api/limarket_api/retrieve_category_product")
    fun searchProductList(
        @Field("product_name") product_name: String,
        @Field("api_key") api_key: String,
        @Field("per_page") per_page: String,
        @Field("page") page: Int,
        @Field("price_range") price_range: String,
        @Field("cat_id") cat_id: String,
        @Field("price_sorting") price_sorting: String,
    ) : Call<SearchProductModel?>?

}