package com.lemon.wanandroid.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.utils.GlideImageLoader
import com.lemon.wanandroid.view.HomeArticleAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels{
        viewModelFactory
    }

    private lateinit var adapter: HomeArticleAdapter

    override fun getContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        banner_view?.setImageLoader(GlideImageLoader())
        refreshlayout?.setEnableRefresh(true)
        refreshlayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getArticle(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getArticle(true)
            }

        })
        article_view?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = HomeArticleAdapter(R.layout.item_home_article)
        article_view?.adapter = adapter
    }

    override fun initData() {
        viewModel.banner.observe(this){listBanner->
            listBanner.data?.run {
                val pathList = mutableListOf<String>()
                forEach {banner->
                    pathList.add(banner.imagePath)
                }
                banner_view?.run {
                    setImages(pathList)
                    isAutoPlay(true)
                    start()
                }
                banner_view.setOnBannerListener {position->
                    val banner = this[position]
                }
            }

        }
        viewModel.article.observe(this){
            adapter.setNewData(it)
            refreshlayout?.finishLoadMore()
            refreshlayout?.finishRefresh()
            adapter.setOnItemClickListener { adapter, view, position ->
                val itemBean = it?.get(position)
            }
        }
    }

}