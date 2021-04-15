package com.emon.offlinecachingwithroom.Room.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey
    var product_id: Int,
    var thumb_image_url: String? = null,
    var category_name: String? = null,
    var price: String? = null,
    var sub_price: String? = null,
    var quantity: String? = null,
    var title: String? = null
): Parcelable
