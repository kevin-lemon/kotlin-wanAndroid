package com.lemon.wanandroid.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.bean.Resource

/**
 * Created by Lemon on 2019/12/8.
 */
abstract class NetWorkResource<RequestType> @MainThread constructor(){

    private val result = MediatorLiveData<Resource<RequestType>>()
    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }
    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when(response.errorCode){
                0->{
                    setValue(Resource.success(processResponse(response)))
                }
                -1->{
                    onFetchFailed()
                    setValue(Resource.error(response.errorMsg, processResponse(response)))
                }
            }
        }
    }
    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }
    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<RequestType>>

    fun asMutableLiveData() = result as MutableLiveData<Resource<RequestType>>
    @WorkerThread
    protected open fun processResponse(response: ApiResponse<RequestType>) = response.data

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}