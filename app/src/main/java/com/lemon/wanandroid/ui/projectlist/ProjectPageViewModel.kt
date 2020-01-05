package com.lemon.wanandroid.ui.projectlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.Project
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.bean.Status
import com.lemon.wanandroid.repository.ProjectRepository
import javax.inject.Inject

open class ProjectPageViewModel @Inject constructor(repository: ProjectRepository) : ViewModel() {
    private var cid: Int = 0
    var projects : MutableLiveData<MutableList<Project.Data>> = MutableLiveData()
    private val _pageNum = MutableLiveData<Int>()
    var isHaveMoreArticle = true
    val pageNum: LiveData<Int>
        get() = _pageNum
    var resourceProject: LiveData<Resource<Project>> = Transformations
        .switchMap(pageNum) { pageNum ->
            pageNum?.let {
                repository.getProject(it,cid )
            }
        }

    open fun lazyInit(cid: Int) {
        this.cid = cid
        resourceProject.observeForever(resourceProjectObserver)
        _pageNum.postValue(1)
    }

    //false为加载更多，true为刷新获取第一页
    fun getProject(isRefresh: Boolean) {
        if (isRefresh) {
            _pageNum.value = 1
            resourceProject.observeForever(resourceProjectObserver)
        } else {
            if (isHaveMoreArticle) {
                _pageNum.value?.let {
                    _pageNum.value = it + 1
                }
                resourceProject.observeForever(resourceProjectObserver)
            }
        }
    }

    private val resourceProjectObserver = Observer<Resource<Project>> { resource ->
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let { data ->
                    isHaveMoreArticle = data.curPage < data.pageCount
                    when (data.curPage) {
                        1 -> {
                            if (projects.value == null) {
                                projects.value = data.datas.toMutableList()
                                projects.postValue(projects.value)
                            }else{
                                projects.value?.clear()
                                projects.value?.addAll(data.datas.toMutableList())
                                projects.postValue(projects.value)
                            }
                        }
                        else -> {
                            projects.value?.addAll(data.datas.toMutableList())
                            projects.postValue(projects.value)
                        }
                    }
                    unregisterResourceProjectObserver()
                }
            }
            Status.ERROR -> {
                unregisterResourceProjectObserver()
            }
            Status.LOADING -> {
            }
        }
    }

    private fun unregisterResourceProjectObserver() {
        resourceProject.removeObserver(resourceProjectObserver)
    }
}