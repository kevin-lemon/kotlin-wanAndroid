package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.NavBean
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationRepository @Inject constructor(private val apiService: ApiService) {

    fun  getNavData() :LiveData<Resource<List<NavBean>>>{
        return object : NetWorkResource<List<NavBean>>(){
            override fun createCall(): LiveData<ApiResponse<List<NavBean>>> {
                return apiService.getNavData()
            }

        }.asLiveData()
    }
}