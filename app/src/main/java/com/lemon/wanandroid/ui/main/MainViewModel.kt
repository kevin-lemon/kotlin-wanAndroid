package com.lemon.wanandroid.ui.main

import androidx.lifecycle.*
import com.lemon.wanandroid.api.ApiResponse
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Lemon on 2019/11/29.
 */
open class MainViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    val userId: String = savedStateHandle["uid"]?:""
    var banner = HomeRepository().getBanner()
    private val _pageNum = MutableLiveData<Int>()
    val pageNum: LiveData<Int>
        get() = _pageNum
    var  article :LiveData<Resource<Article>> = Transformations
    .switchMap(_pageNum) { pageNum ->
        pageNum?.let {
            HomeRepository().getArticle(it)
        }
    }
    init {
        getBanner()
        getArticle(0)
    }
    fun getBanner(){
        banner = HomeRepository().getBanner()
    }
    fun getArticle(pageNum:Int){
        _pageNum.value = pageNum
    }
}