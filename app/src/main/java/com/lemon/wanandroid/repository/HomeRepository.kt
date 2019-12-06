package com.lemon.wanandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.HomeService
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.utils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


/**
 * Created by Lemon on 2019/11/29.
 */
class HomeRepository(
) {
    lateinit var data : LiveData<ApiResponse<Banner>>
    val homeService: HomeService = provideHomeService()
    fun provideHomeService(): HomeService {
        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HomeService::class.java)
    }
    fun getArticle(pageNum: String) {
        data = homeService.getBanner()
    }
}