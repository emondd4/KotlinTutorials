package com.emon.chipskotlin.Utils

import android.content.Context
import android.content.SharedPreferences
import com.emon.chipskotlin.SearchProductModel.SearchProductCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.collections.ArrayList

open class SharedPref {

    companion object {

        private var mSharedPref: SharedPreferences? = null
        val NAME = "com.bdtask.limarket"

        fun init(context: Context) {
            if (mSharedPref == null){
                mSharedPref = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
            }
        }

        fun read(key: String?, defValue: String?): String? {
            return mSharedPref!!.getString(key, defValue)
        }

        fun write(key: String?, value: String?) {
            val prefsEditor = mSharedPref!!.edit()
            prefsEditor.putString(key, value)
            prefsEditor.apply()
        }

        fun saveArrayList(key: String?,list: ArrayList<String?>?) {
            val prefsEditor = mSharedPref!!.edit()
            val gson = Gson()
            val json: String = gson.toJson(list)
            prefsEditor.putString(key, json)
            prefsEditor.apply()
        }

        fun getArrayList(key: String?,defValue: String?): ArrayList<String?>? {
            val gson = Gson()
            val json: String? = mSharedPref!!.getString(key, defValue)
            val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
            return gson.fromJson(json, type)
        }

        fun saveSearchCategoryObject(key: String?, list: ArrayList<SearchProductCategory?>){
            val prefsEditor = mSharedPref!!.edit()
            val gson = Gson()
            val json: String = gson.toJson(list)
            prefsEditor.putString(key, json)
            prefsEditor.apply()
        }

        fun getSearchCategoryObject(key: String?, defValue: String?): ArrayList<SearchProductCategory?>?{
            val gson = Gson()
            val json: String? = mSharedPref!!.getString(key, defValue)
            val type: Type = object : TypeToken<ArrayList<SearchProductCategory?>?>() {}.type
            return gson.fromJson(json, type)
        }



//        fun saveMerchantObject(key: String?, list: ArrayList<MerchantInfo?>) {
//            val prefsEditor = mSharedPref!!.edit()
//            val gson = Gson()
//            val json: String = gson.toJson(list)
//            prefsEditor.putString(key, json)
//            prefsEditor.apply()
//        }
//
//        fun getMerchantObject(key: String?,defValue: String?): ArrayList<MerchantInfo?>? {
//            val gson = Gson()
//            val json: String? = mSharedPref!!.getString(key, defValue)
//            val type: Type = object : TypeToken<ArrayList<MerchantInfo?>?>() {}.type
//            return gson.fromJson(json, type)
//        }

    }
}