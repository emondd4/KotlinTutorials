package com.example.mvvm.Repository

import com.example.mvvm.Database.ArticleDatabase
import com.example.mvvm.Retrofit.RetrofitInstance

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}