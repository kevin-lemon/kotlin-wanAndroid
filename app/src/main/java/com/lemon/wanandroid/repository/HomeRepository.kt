package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.ArticleBean
import com.lemon.wanandroid.bean.BannerBean
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject


/**
 * Created by Lemon on 2019/11/29.
 */
class HomeRepository @Inject constructor(private val apiService: ApiService) {

    fun getBanner(): LiveData<Resource<List<BannerBean>>> {
        return object :NetWorkResource<List<BannerBean>>(){
            override fun createCall(): LiveData<ApiResponse<List<BannerBean>>> = apiService.getBanner()
        }.asLiveData()
    }
    fun getArticle(pageNum:Int): MutableLiveData<Resource<ArticleBean>> {
        return object :NetWorkResource<ArticleBean>(){
            override fun createCall(): LiveData<ApiResponse<ArticleBean>> = apiService.getArticle(pageNum)
        }.asMutableLiveData()
    }
}