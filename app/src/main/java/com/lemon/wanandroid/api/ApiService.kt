package com.lemon.wanandroid.api

import androidx.lifecycle.LiveData
import com.lemon.wanandroid.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{
    //--------- 首页API ----------//
    @GET("article/list/{pageNum}/json")
    fun getArticle(@Path("pageNum") pageNum :Int): LiveData<ApiResponse<ArticleBean>>

    @GET("banner/json")
    fun getBanner(): LiveData<ApiResponse<List<BannerBean>>>
    //--------- 首页API end ----------//

    //--------- 项目API ----------//
    @GET("project/tree/json")
    fun getProjectTabs(): LiveData<ApiResponse<List<ProjectTabBean>>>

    @GET("project/list/{page}/json")
    fun getProject(@Path("page") page:Int,@Query("cid") cid : Int) : LiveData<ApiResponse<ProjectBean>>

    //--------- 项目end ----------//

    //--------- 公众号API ----------//
    @GET("wxarticle/chapters/json")
    fun getPublicArticle() : LiveData<ApiResponse<List<PublicArticleBean>>>

    @GET("wxarticle/list/{ID}/{page}/json")
    fun getPublicArticleData(@Path("page") page:Int,@Path("ID") id:Int) : LiveData<ApiResponse<PublicArticleDataBean>>
    //--------- 公众号end ----------//

    //--------- 体系API ----------//
    @GET("tree/json")
    fun getSystemMenu() :LiveData<ApiResponse<List<SystemMenuBean>>>

    @GET("article/list/{page}/json")
    fun getSystemData(@Path("page") page:Int,@Query("cid")cid : Int):LiveData<ApiResponse<SystemDataBean>>
    //--------- 体系end ----------//

    //--------- 导航API ----------//
    @GET("navi/json")
    fun getNavData() :LiveData<ApiResponse<List<NavBean>>>
    //--------- 导航end ----------//
}