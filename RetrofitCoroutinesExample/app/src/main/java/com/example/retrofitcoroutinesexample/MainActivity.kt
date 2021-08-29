package com.example.retrofitcoroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitcoroutinesexample.Model.CommentsModelItem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
/////////////////////////////normal retrofit request///////////////////////////
//        api.getComments().enqueue(object : Callback<List<CommentsModelItem>> {
//            override fun onFailure(call: Call<List<CommentsModelItem>>, t: Throwable) {
//                Log.e(TAG,"error: $t")
//            }
//
//            override fun onResponse(
//                call: Call<List<CommentsModelItem>>,
//                response: Response<List<CommentsModelItem>>
//            ) {
//                if (response.isSuccessful){
//                    response.body()?.let {
//                        for (comment in it){
//                            Log.d(TAG,comment.toString())
//                        }
//                    }
//                }
//            }
//        })

        ///////////////////coroutines//////////////////////
        GlobalScope.launch(Dispatchers.IO){
            val comments = api.getComments().await()
            for (comment in comments){
                Log.wtf(TAG, "Coroutines Response: $comment")
            }
        }
    }
}