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
open class HomeViewModel @Inject constructor(repository: HomeRepository): ViewModel(){
    var banner :LiveData<Resource<List<Banner>>> = MutableLiveData()

    var article : MutableLiveData<MutableList<Article.Data>> = MutableLiveData()

    private val _pageNum = MutableLiveData<Int>()

    val pageNum: LiveData<Int>
        get() = _pageNum

    private var haveNextPageState : Boolean = true

    private var  resourcAarticle :LiveData<Resource<Article>> = Transformations
    .switchMap(pageNum) { pageNum ->
        pageNum?.let {
            repository.getArticle(it)
        }
    }

    private val resourceArticleObserver = Observer<Resource<Article>> {
            resource->
        when(resource.status){
            Status.SUCCESS->{
                resource.data?.let {data->
                    if (article.value == null){
                        article.value = data.datas.toMutableList()
                        article.postValue(article.value)
                    }else{
                        article.value?.addAll(data.datas.toMutableList())
                        article.postValue(article.value)
                    }
                }
                unregisterResourcAarticleObserver()
            }
            Status.ERROR->{
                haveNextPageState = false
                unregisterResourcAarticleObserver()
            }
        }
    }
    init {
        banner = repository.getBanner()
        resourcAarticle.observeForever(resourceArticleObserver)
        _pageNum.postValue(0)
    }

    fun getArticle(){
        if (haveNextPageState){
            resourcAarticle.observeForever(resourceArticleObserver)
            _pageNum.value?.let {
                _pageNum.postValue(it+1)
            }
        }
    }

   private fun unregisterResourcAarticleObserver(){
       resourcAarticle.removeObserver(resourceArticleObserver)
   }
}
