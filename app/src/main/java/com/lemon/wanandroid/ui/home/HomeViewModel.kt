package com.lemon.wanandroid.ui.home

import androidx.lifecycle.*
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.bean.Banner
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.bean.Status
import com.lemon.wanandroid.repository.HomeRepository
import javax.inject.Inject

/**
 * Created by Lemon on 2019/11/29.
 */
open class HomeViewModel @Inject constructor(repository: HomeRepository) : ViewModel() {
    var banner: LiveData<Resource<List<Banner>>> = MutableLiveData()

    var article: MutableLiveData<MutableList<Article.Data>> = MutableLiveData()

    private val _pageNum = MutableLiveData<Int>()

    val pageNum: LiveData<Int>
        get() = _pageNum
    var isHaveMoreArticle = true
    private var resourceArticle: LiveData<Resource<Article>> = Transformations
        .switchMap(pageNum) { pageNum ->
            pageNum?.let {
                repository.getArticle(it)
            }
        }

    private val resourceArticleObserver = Observer<Resource<Article>> { resource ->
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let { data ->
                    if (article.value == null) {
                        article.value = data.datas.toMutableList()
                        article.postValue(article.value)
                    }
                    isHaveMoreArticle = data.curPage < data.pageCount
                    when (data.curPage) {
                        1 -> {
                            article.value?.clear()
                            article.value?.addAll(data.datas.toMutableList())
                            article.postValue(article.value)
                        }
                        else -> {
                            article.value?.addAll(data.datas.toMutableList())
                            article.postValue(article.value)
                        }
                    }
                }
                unregisterResourceArticleObserver()
            }
            Status.ERROR -> {
                unregisterResourceArticleObserver()
            }
            Status.LOADING-> {

            }
        }
    }

    init {
        banner = repository.getBanner()
        resourceArticle.observeForever(resourceArticleObserver)
        _pageNum.postValue(0)
    }

    //false为加载更多，true为刷新获取第一页
    fun getArticle(isRefresh: Boolean) {
        if (isRefresh) {
            _pageNum.value = 0
            resourceArticle.observeForever(resourceArticleObserver)
        } else {
            if (isHaveMoreArticle) {
                _pageNum.value?.let {
                    _pageNum.value = it + 1
                }
                resourceArticle.observeForever(resourceArticleObserver)
            }
        }
    }

    private fun unregisterResourceArticleObserver() {
        resourceArticle.removeObserver(resourceArticleObserver)
    }
}
