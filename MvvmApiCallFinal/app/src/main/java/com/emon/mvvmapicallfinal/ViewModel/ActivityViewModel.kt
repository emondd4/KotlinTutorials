package com.emon.mvvmapicallfinal.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emon.mvvmapicallfinal.Model.BaseResponse
import com.emon.mvvmapicallfinal.Repository.ActivityRepository
import com.emon.mvvmapicallfinal.Retrofit.ApiState
import com.emon.mvvmapicallfinal.Retrofit.Status
import com.emon.mvvmapicallfinal.Utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActivityViewModel: ViewModel() {

    //Create a Repository and pass the api service we created in AppConfig file
    private val repository = ActivityRepository(
        AppConfig.ApiService()
    )

    /**
     * A commentData mutableStateflow which will store the Helper class CommentApiState
     * which may store the api data or the failure state.
     * Views can collect this data in background, as data is emitted whenever value changes, similar to livedata.
     * Set the initial Value to Loading.
     */
    val photosState = MutableStateFlow(
        ApiState(
            Status.LOADING,
            BaseResponse(), ""
        )
    )

    init {
        //Initiate a starting search
        getAllPhotos()
    }

    fun getAllPhotos(){

        //Since Network Calls takes time,Set the initial value to loading state
        photosState.value = ApiState.loading()

        //ApiCalls takes some time, So it has to be run and background thread. Using viewModelScope to call the api
        viewModelScope.launch {

            //Collecting the data emitted by the function in repository
            repository.getPhotos()
                //If any errors occurs like 404 not found or invalid query, set the state to error State to show some info
                //on screen
                .catch {
                    photosState.value =
                        ApiState.error(it.message.toString())
                }
                //If Api call is succeeded, set the State to Success and set the response data to data received from api
                .collect {
                    photosState.value = ApiState.success(it.data)
                }
        }

    }

}