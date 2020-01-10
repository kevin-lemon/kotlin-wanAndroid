package com.lemon.wanandroid.ui.articlepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.PublicArticleData
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.bean.Status
import com.lemon.wanandroid.repository.ArticleRepository
import javax.inject.Inject

class ArticlePageViewModel @Inject constructor(repository: ArticleRepository) : ViewModel(){
    private var id: Int = 0
    var articles : MutableLiveData<MutableList<PublicArticleData.Data>> = MutableLiveData()
    private val _pageNum = MutableLiveData<Int>()
    var isHaveMoreData = true
    val pageNum: LiveData<Int>
        get() = _pageNum
    var resourceArticleData: LiveData<Resource<PublicArticleData>> = Transformations
        .switchMap(pageNum) { pageNum ->
            pageNum?.let {
                repository.getPublicArticleData(it,id)
            }
        }

    open fun lazyInit(id: Int) {
        this.id = id
        resourceArticleData.observeForever(resourceArticleDataObserver)
        _pageNum.postValue(1)
    }

    //false为加载更多，true为刷新获取第一页
    fun getArticleData(isRefresh: Boolean) {
        if (isRefresh) {
            _pageNum.value = 1
            resourceArticleData.observeForever(resourceArticleDataObserver)
        } else {
            if (isHaveMoreData) {
                _pageNum.value?.let {
                    _pageNum.value = it + 1
                }
                resourceArticleData.observeForever(resourceArticleDataObserver)
            }
        }
    }

    private val resourceArticleDataObserver = Observer<Resource<PublicArticleData>> { resource ->
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let { data ->
                    isHaveMoreData = data.curPage < data.pageCount
                    when (data.curPage) {
                        1 -> {
                            if (articles.value == null) {
                                articles.value = data.datas.toMutableList()
                                articles.postValue(articles.value)
                            }else{
                                articles.value?.clear()
                                articles.value?.addAll(data.datas.toMutableList())
                                articles.postValue(articles.value)
                            }
                        }
                        else -> {
                            articles.value?.addAll(data.datas.toMutableList())
                            articles.postValue(articles.value)
                        }
                    }
                    unregisterResourceArticleDataObserver()
                }
            }
            Status.ERROR -> {
                unregisterResourceArticleDataObserver()
            }
            Status.LOADING -> {
            }
        }
    }

    private fun unregisterResourceArticleDataObserver() {
        resourceArticleData.removeObserver(resourceArticleDataObserver)
    }
}