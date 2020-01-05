package com.lemon.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.api.ApiService
import com.lemon.wanandroid.bean.Project
import com.lemon.wanandroid.bean.ProjectTab
import com.lemon.wanandroid.bean.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(private val apiService: ApiService) {

    fun getProjectTabs(): LiveData<Resource<List<ProjectTab>>> {
        return object : NetWorkResource<List<ProjectTab>>() {
            override fun createCall(): LiveData<ApiResponse<List<ProjectTab>>> {
                return apiService.getProjectTabs()
            }
        }.asLiveData()
    }

    fun getProject(page: Int,cid: Int) : MutableLiveData<Resource<Project>>{
        return object : NetWorkResource<Project>(){
            override fun createCall(): LiveData<ApiResponse<Project>> {
                return apiService.getProject(page,cid)
            }
        }.asMutableLiveData()
    }
}