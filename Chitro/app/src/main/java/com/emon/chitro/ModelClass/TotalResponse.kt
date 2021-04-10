package com.emon.chitro.ModelClass

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TotalResponse(val total: Int, val totalHits: Int, val hits: ArrayList<PhotosModelClass?>? )