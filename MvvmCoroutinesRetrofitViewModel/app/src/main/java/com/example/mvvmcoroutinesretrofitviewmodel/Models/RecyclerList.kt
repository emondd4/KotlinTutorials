package com.example.mvvmcoroutinesretrofitviewmodel.Models

data class RecyclerList(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)