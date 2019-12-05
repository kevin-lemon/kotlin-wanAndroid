package com.lemon.wanandroid.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.lemon.wanandroid.bean.Resource

/**
 * Created by wxk on 2019/12/4.
 */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(){

    private val result = MediatorLiveData<Resource<ResultType>>()
    init {
        result.value = Resource.loading(null)
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
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<RequestType>
}