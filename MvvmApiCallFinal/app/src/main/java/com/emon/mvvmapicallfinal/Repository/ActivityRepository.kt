package com.emon.mvvmapicallfinal.Repository

import com.emon.mvvmapicallfinal.Model.BaseResponse
import com.emon.mvvmapicallfinal.Retrofit.ApiService
import com.emon.mvvmapicallfinal.Retrofit.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

class ActivityRepository(private val apiService: ApiService) {

    /***
     * Function which will call the api and it will return a Flow.
     * Return a flow, It asynchronously performs calculation or function
     * and the emit function emits the data to the receivers which are listening
     * to this flow.
     */

    suspend fun getPhotos(): Flow<ApiState<BaseResponse>> {
        return flow {

            //get the images Data from the api
            val photos= apiService.getPhotos()

            //Emit this data wrapped in the helper class [ApiState]
            emit(ApiState.success(photos))

        }.flowOn(Dispatchers.IO)
    }

}