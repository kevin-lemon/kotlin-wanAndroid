package com.lemon.wanandroid.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.PublicArticleBean
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.ArticleRepository
import javax.inject.Inject

open class ArticleViewModel @Inject constructor(val repository: ArticleRepository) : ViewModel(){

    var articleTabs: LiveData<Resource<List<PublicArticleBean>>> = MutableLiveData()

    init {
        articleTabs = repository.getPublicArticle()
    }

    fun getPublicArtice(){
        repository.getPublicArticle()
    }
}