package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.bean.SystemDataBean
import com.lemon.wanandroid.bean.SystemMenuBean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SystemRepository @Inject constructor(private val apiService: ApiService) {


    fun getSystemMenu():LiveData<Resource<List<SystemMenuBean>>>{
        return object : NetWorkResource<List<SystemMenuBean>>(){
            override fun createCall(): LiveData<ApiResponse<List<SystemMenuBean>>> {
                return apiService.getSystemMenu()
            }
        }.asLiveData()
    }

    fun getSystemData(page : Int,cid : Int):LiveData<Resource<SystemDataBean>>{
        return object : NetWorkResource<SystemDataBean>(){
            override fun createCall(): LiveData<ApiResponse<SystemDataBean>> {
                return apiService.getSystemData(page,cid)
            }
        }.asLiveData()
    }
}