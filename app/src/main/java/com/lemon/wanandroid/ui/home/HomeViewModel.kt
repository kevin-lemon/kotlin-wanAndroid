package com.lemon.wanandroid.ui.home

import androidx.lifecycle.*
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.HomeRepository
import javax.inject.Inject

/**
 * Created by Lemon on 2019/11/29.
 */
open class HomeViewModel @Inject constructor(repository: HomeRepository): ViewModel() {
    var banner :LiveData<Resource<List<Banner>>> = MutableLiveData()
    private val _pageNum = MutableLiveData<Int>()
    val pageNum: LiveData<Int>
        get() = _pageNum
    var  article :LiveData<Resource<Article>> = Transformations
    .switchMap(_pageNum) { pageNum ->
        pageNum?.let {
            repository.getArticle(it)
        }
    }

    init {
        banner = repository.getBanner()
        getArticle(0)
    }

    private fun getArticle(pageNum:Int){
        _pageNum.value = pageNum
    }
}