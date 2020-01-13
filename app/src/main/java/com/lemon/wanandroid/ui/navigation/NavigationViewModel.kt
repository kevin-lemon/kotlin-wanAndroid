package com.lemon.wanandroid.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.NavBean
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.NavigationRepository
import javax.inject.Inject

open class NavigationViewModel @Inject constructor(var repository: NavigationRepository) : ViewModel(){

    var navDatas : LiveData<Resource<List<NavBean>>> = MutableLiveData()
    init {
        navDatas = repository.getNavData()
    }
}