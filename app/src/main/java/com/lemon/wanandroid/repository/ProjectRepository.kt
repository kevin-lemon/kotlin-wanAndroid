package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.ProjectBean
import com.lemon.wanandroid.bean.ProjectTabBean
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(private val apiService: ApiService) {

    fun getProjectTabs(): LiveData<Resource<List<ProjectTabBean>>> {
        return object : NetWorkResource<List<ProjectTabBean>>() {
            override fun createCall(): LiveData<ApiResponse<List<ProjectTabBean>>> {
                return apiService.getProjectTabs()
            }
        }.asLiveData()
    }

    fun getProject(page: Int,cid: Int) : MutableLiveData<Resource<ProjectBean>>{
        return object : NetWorkResource<ProjectBean>(){
            override fun createCall(): LiveData<ApiResponse<ProjectBean>> {
                return apiService.getProject(page,cid)
            }
        }.asMutableLiveData()
    }
}