package com.emon.offlinecachingwithroom.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emon.offlinecachingwithroom.Room.Model.Product

@Database(entities = [Product::class], version = 1,exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object{

        @Volatile
        private  var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instanse = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instanse
                return  instanse
            }

        }

    }

}