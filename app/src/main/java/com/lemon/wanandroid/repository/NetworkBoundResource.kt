package com.lemon.wanandroid.repository

import android.widget.Switch
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.bean.Resource
import java.util.*

/**
 * Created by wxk on 2019/12/4.
 */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(){

    private val result = MediatorLiveData<Resource<ResultType>>()
    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        fetchFromNetwork(dbSource)
//        result.addSource(dbSource){ data ->
//            result.removeSource(dbSource)
//            if(shouldFetch(data)){
//                fetchFromNetwork(dbSource)
//            }else{
//                result.addSource(dbSource){newData ->
//                    setValue(Resource.success(newData))
//                }
//            }
//        }
    }
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.errorCode){
                0->{
                    saveCallResult(processResponse(response))
                    setValue(Resource.success(result.value?.data))
                }
                -1->{
                    onFetchFailed()
                    setValue(Resource.error(response.errorMsg, null))
                }
            }
        }
    }
    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }
    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiResponse<RequestType>) = response.data
    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}