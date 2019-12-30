package com.lemon.wanandroid.ui.home

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.WanApp
import com.lemon.wanandroid.utils.GlideImageLoader
import com.lemon.wanandroid.adapter.HomeArticleAdapter
import com.scwang.smartrefresh.header.DropBoxHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import kotlin.math.abs

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: HomeArticleAdapter

    override fun getContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        banner_view?.setImageLoader(GlideImageLoader())
        refreshlayout?.setEnableRefresh(true)
        refreshlayout?.setReboundDuration(300)
        refreshlayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getArticle(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getArticle(true)
            }

        })
        article_view?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter =
            HomeArticleAdapter(R.layout.item_article)
        article_view?.adapter = adapter
        refreshlayout.setRefreshHeader(DropBoxHeader(context))
        refreshlayout.setRefreshFooter(ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale))
        colling_tool_bar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                WanApp.getContext(),
                R.color.colorWhite
            )
        )
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            colling_tool_bar.title =
                if (abs(verticalOffset) >= appBarLayout.totalScrollRange) getString(R.string.item_home) else ""
        })
    }

    override fun initData() {
        viewModel.banner.observe(this) { listBanner ->
            listBanner.data?.run {
                val pathList = mutableListOf<String>()
                forEach { banner ->
                    pathList.add(banner.imagePath)
                }
                banner_view?.run {
                    setImages(pathList)
                    isAutoPlay(true)
                    start()
                }
                banner_view.setOnBannerListener { position ->
                    val banner = this[position]
                }
            }

        }
        viewModel.article.observe(this) {
            adapter.setNewData(it)
            refreshlayout?.finishRefresh()
            if (viewModel.isHaveMoreArticle){
                refreshlayout?.finishLoadMore()
            }else{
                refreshlayout.finishLoadMoreWithNoMoreData()
            }
            adapter.setOnItemClickListener { adapter, view, position ->
                val itemBean = it?.get(position)
            }
        }
    }

}