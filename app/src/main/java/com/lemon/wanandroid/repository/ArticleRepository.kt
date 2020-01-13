package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.PublicArticleBean
import com.lemon.wanandroid.bean.PublicArticleDataBean
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val apiService: ApiService) {

    fun getPublicArticle():LiveData<Resource<List<PublicArticleBean>>>{
        return object : NetWorkResource<List<PublicArticleBean>>(){
            override fun createCall(): LiveData<ApiResponse<List<PublicArticleBean>>> {
                return apiService.getPublicArticle()
            }
        }.asLiveData()
    }

    fun getPublicArticleData(page: Int,id: Int) : MutableLiveData<Resource<PublicArticleDataBean>> {
        return object : NetWorkResource<PublicArticleDataBean>(){
            override fun createCall(): LiveData<ApiResponse<PublicArticleDataBean>> {
                return apiService.getPublicArticleData(page,id)
            }
        }.asMutableLiveData()
    }

}