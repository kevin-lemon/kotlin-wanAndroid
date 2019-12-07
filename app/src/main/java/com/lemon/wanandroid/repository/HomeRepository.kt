package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.HomeService
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.utils.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Lemon on 2019/11/29.
 */
class HomeRepository(
) {
    private val result = MediatorLiveData<Resource<Banner>>()
    val homeService: HomeService = provideHomeService()

    
    fun provideHomeService(): HomeService {
        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HomeService::class.java)
    }

    fun getBanner(): LiveData<Resource<List<Banner>>> {
        return object : NetworkBoundResource<List<Banner>,List<Banner>>(){
            override fun saveCallResult(item: List<Banner>) {
            }

            override fun shouldFetch(data: List<Banner>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Banner>> {
                val data = MutableLiveData<List<Banner>>()
                return data
            }

            override fun createCall(): LiveData<ApiResponse<List<Banner>>> {
                return homeService.getBanner()
            }

        }.asLiveData()
    }
}