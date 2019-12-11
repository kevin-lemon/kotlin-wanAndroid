package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Lemon on 2019/11/29.
 */
@Singleton
class HomeRepository @Inject constructor(private val apiService: ApiService) {

    fun getBanner(): LiveData<Resource<List<Banner>>> {
        return object :NetWorkResource<List<Banner>>(){
            override fun createCall(): LiveData<ApiResponse<List<Banner>>> = apiService.getBanner()
        }.asLiveData()
    }
    fun getArticle(pageNum:Int):LiveData<Resource<Article>>{
        return object :NetWorkResource<Article>(){
            override fun createCall(): LiveData<ApiResponse<Article>> = apiService.getArticle(pageNum)
        }.asLiveData()
    }
}