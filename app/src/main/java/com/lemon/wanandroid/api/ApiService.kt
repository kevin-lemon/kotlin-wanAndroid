package com.lemon.wanandroid.api

import androidx.lifecycle.LiveData
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    //--------- 首页API ----------//
    @GET("article/list/{pageNum}/json")
    fun getArticle(@Path("pageNum") pageNum :Int): LiveData<ApiResponse<Article>>

    @GET("banner/json")
    fun getBanner(): LiveData<ApiResponse<List<Banner>>>
    //--------- 首页API end ----------//
}