package com.lemon.wanandroid.ui.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.ProjectTabBean
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.bean.SystemMenuBean
import com.lemon.wanandroid.repository.SystemRepository
import javax.inject.Inject

open class SystemViewModel @Inject constructor(var repository: SystemRepository) : ViewModel(){

    var systemMenus: LiveData<Resource<List<SystemMenuBean>>> = MutableLiveData()

    init {
        systemMenus = repository.getSystemMenu()
    }
}