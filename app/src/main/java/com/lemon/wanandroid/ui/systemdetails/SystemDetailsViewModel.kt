package com.lemon.wanandroid.ui.systemdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.bean.Status
import com.lemon.wanandroid.bean.SystemDataBean
import com.lemon.wanandroid.repository.SystemRepository
import javax.inject.Inject


class SystemDetailsViewModel @Inject constructor(repository: SystemRepository)  : ViewModel(){
    private var cid: Int = 0
    var systems : MutableLiveData<MutableList<SystemDataBean.Data>> = MutableLiveData()
    private val _pageNum = MutableLiveData<Int>()
    var isHaveMoreSystem = true
    val pageNum: LiveData<Int>
        get() = _pageNum
    var resourceSystem: LiveData<Resource<SystemDataBean>> = Transformations
        .switchMap(pageNum) { pageNum ->
            pageNum?.let {
                repository.getSystemData(it,cid)
            }
        }

    open fun lazyInit(cid: Int) {
        this.cid = cid
        resourceSystem.observeForever(resourceSystemObserver)
        _pageNum.postValue(0)
    }

    fun getSystemDetails(isRefresh: Boolean) {
        if (isRefresh) {
            _pageNum.value = 0
            resourceSystem.observeForever(resourceSystemObserver)
        } else {
            if (isHaveMoreSystem) {
                _pageNum.value?.let {
                    _pageNum.value = it + 1
                }
                resourceSystem.observeForever(resourceSystemObserver)
            }
        }
    }

    private val resourceSystemObserver = Observer<Resource<SystemDataBean>> { resource ->
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let { data ->
                    isHaveMoreSystem = data.curPage < data.pageCount-1
                    when (data.curPage) {
                        1 -> {
                            if (systems.value == null) {
                                systems.value = data.datas.toMutableList()
                                systems.postValue(systems.value)
                            }else{
                                systems.value?.clear()
                                systems.value?.addAll(data.datas.toMutableList())
                                systems.postValue(systems.value)
                            }
                        }
                        else -> {
                            systems.value?.addAll(data.datas.toMutableList())
                            systems.postValue(systems.value)
                        }
                    }
                    unregisterresourceSystemObserver()
                }
            }
            Status.ERROR -> {
                unregisterresourceSystemObserver()
            }
            Status.LOADING -> {
            }
        }
    }

    private fun unregisterresourceSystemObserver() {
        resourceSystem.removeObserver(resourceSystemObserver)
    }
}