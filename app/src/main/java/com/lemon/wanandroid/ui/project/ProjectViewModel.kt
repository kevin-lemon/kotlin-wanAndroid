package com.lemon.wanandroid.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.ProjectTabBean
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.ProjectRepository
import javax.inject.Inject

open class ProjectViewModel @Inject constructor(repository: ProjectRepository) : ViewModel(){

    var projectTabs: LiveData<Resource<List<ProjectTabBean>>> = MutableLiveData()

    init {
        projectTabs = repository.getProjectTabs()
    }
}