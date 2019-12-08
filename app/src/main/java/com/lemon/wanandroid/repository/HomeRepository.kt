package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.HomeService
import com.lemon.wanandroid.bean.Article
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
        return object :NetWorkResource<List<Banner>>(){
            override fun createCall(): LiveData<ApiResponse<List<Banner>>> = homeService.getBanner()
        }.asLiveData()
    }
    fun getArticle(pageNum:String):LiveData<Resource<Article>>{
        return object :NetWorkResource<Article>(){
            override fun createCall(): LiveData<ApiResponse<Article>> = homeService.getArticle(pageNum)
        }.asLiveData()
    }
}