package com.lemon.wanandroid.ui.article

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.ArticlePageAdapter
import com.lemon.wanandroid.bean.FragmentItemBean
import com.lemon.wanandroid.ui.articlepage.ArticlePageFragment
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject


class ArticleFragment : BaseFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ArticleViewModel by viewModels {
        viewModelFactory
    }
    private var fragmentItems = mutableListOf<FragmentItemBean>()
    private lateinit var adapter: ArticlePageAdapter
    override fun getContentViewId(): Int {
        return R.layout.fragment_article
    }

    override fun initView() {
        adapter =  ArticlePageAdapter(childFragmentManager,fragmentItems)
        article_view.adapter = adapter
        article_tabs?.setupWithViewPager(article_view)
    }

    override fun initData() {
        viewModel.articleTabs.observe(this){
            it.data?.run{
                val fragmentItems = mutableListOf<FragmentItemBean>()
                forEach{articleTab->
                    fragmentItems.add(FragmentItemBean(articleTab.name,
                        ArticlePageFragment.newInstance(articleTab.id)))
                }
                adapter.setData(fragmentItems)
            }
        }
    }


}