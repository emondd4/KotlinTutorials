package com.emon.chitro.ModelClass


data class PhotosModelClass(val id: Int,val pageURL: String,val type: String,val tags: String,val previewURL: String,
                            val previewWidth: Int,val previewHeight: Int,val webformatURL: String,val webformatWidth: Int,
                            val webformatHeight: Int,val largeImageURL: String,val imageWidth: Int,val imageHeight: Int,
                            val imageSize: Int,val views: Int,val downloads: Int,val favorites: Int,val likes: Int,
                            val comments: Int,val userId: Int,val user: String,val userImageURL: String)