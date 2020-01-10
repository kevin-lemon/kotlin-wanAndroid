package com.lemon.wanandroid.ui.articlepage

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.ArticleListAdapter
import com.lemon.wanandroid.ui.article.ArticleFragmentDirections
import com.scwang.smartrefresh.header.DropBoxHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_article_page.*
import javax.inject.Inject


private const val ID = "id"
class ArticlePageFragment : BaseFragment(){

    private lateinit var adapter: ArticleListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ArticlePageViewModel by viewModels {
        viewModelFactory
    }
    companion object {
        fun newInstance(id : Int): ArticlePageFragment {
            return ArticlePageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID, id)
                }
            }
        }
    }
    override fun getContentViewId(): Int {
        return R.layout.fragment_article_page
    }

    override fun initView() {
        refreshlayout?.setEnableRefresh(true)
        refreshlayout?.setReboundDuration(300)
        refreshlayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getArticleData(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getArticleData(true)
            }

        })
        project_view?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        project_view?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = ArticleListAdapter(R.layout.item_public_article)
        project_view?.adapter = adapter
        refreshlayout.setRefreshHeader(DropBoxHeader(context))
        refreshlayout.setRefreshFooter(ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale))
    }

    override fun initData() {
        viewModel.articles.observe(this){
            adapter.setNewData(it)
            refreshlayout?.finishRefresh()
            if (viewModel.isHaveMoreData) {
                refreshlayout?.finishLoadMore()
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData()
            }
            adapter.setOnItemClickListener { adapter, view, position ->
                val itemBean = it?.get(position)
                itemBean?.let {it->
                    val action = ArticleFragmentDirections.actionToDetailsWebFragment(it.link,it.title,it.author)
                    Navigation.findNavController(getView()!!).navigate(action)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lazyInitData()
    }

    private fun lazyInitData() {
        arguments?.let {
            viewModel.lazyInit(it.getInt(ID))
        }
    }
}