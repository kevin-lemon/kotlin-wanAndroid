package com.lemon.wanandroid.api

import androidx.lifecycle.LiveData
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.BaseApi
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by wxk on 2019/12/6.
 */

interface HomeService{

    @GET("article/list/{pageNum}/json")
    fun getArticle(@Path("pageNum") pageNum :String): LiveData<ApiResponse<BaseApi<List<Article>>>>

    @GET("banner/json")
    fun getBanner(): LiveData<ApiResponse<BaseApi<List<Banner>>>>
}