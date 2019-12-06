package com.lemon.wanandroid.ui.main

import androidx.lifecycle.*
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.HomeRepository
import javax.inject.Inject

/**
 * Created by Lemon on 2019/11/29.
 */
open class MainViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    val userId: String = savedStateHandle["uid"]?:""

    fun getBanner(){

    }
}