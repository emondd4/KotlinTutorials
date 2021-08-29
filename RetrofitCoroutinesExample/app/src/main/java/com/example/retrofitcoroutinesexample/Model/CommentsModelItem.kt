package com.example.retrofitcoroutinesexample.Model

data class CommentsModelItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)