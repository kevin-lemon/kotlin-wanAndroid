package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.PublicArticle
import com.lemon.wanandroid.bean.PublicArticleData
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val apiService: ApiService) {

    fun getPublicArticle():LiveData<Resource<List<PublicArticle>>>{
        return object : NetWorkResource<List<PublicArticle>>(){
            override fun createCall(): LiveData<ApiResponse<List<PublicArticle>>> {
                return apiService.getPublicArticle()
            }
        }.asLiveData()
    }

    fun getPublicArticleData(page: Int,id: Int) : MutableLiveData<Resource<PublicArticleData>> {
        return object : NetWorkResource<PublicArticleData>(){
            override fun createCall(): LiveData<ApiResponse<PublicArticleData>> {
                return apiService.getPublicArticleData(page,id)
            }
        }.asMutableLiveData()
    }

}