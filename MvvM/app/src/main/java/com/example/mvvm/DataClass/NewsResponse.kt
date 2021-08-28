package com.example.mvvm.DataClass

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)