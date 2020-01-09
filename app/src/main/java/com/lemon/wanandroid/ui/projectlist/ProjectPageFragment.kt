package com.lemon.wanandroid.ui.projectlist

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.ProjectListAdapter
import com.lemon.wanandroid.ui.project.ProjectFragmentDirections
import com.scwang.smartrefresh.header.DropBoxHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.refreshlayout
import kotlinx.android.synthetic.main.fragment_project_list.*
import javax.inject.Inject

private const val CID = "cid"

class ProjectListFragment : BaseFragment() {

    private lateinit var adapter: ProjectListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ProjectPageViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance(cid: Int): ProjectListFragment {
            return ProjectListFragment().apply {
                arguments = Bundle().apply {
                    putInt(CID, cid)
                }
            }
        }
    }

    override fun getContentViewId(): Int {
        return R.layout.fragment_project_list
    }

    override fun initView() {
        refreshlayout?.setEnableRefresh(true)
        refreshlayout?.setReboundDuration(300)
        refreshlayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getProject(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getProject(true)
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
        adapter = ProjectListAdapter(R.layout.item_project)
        project_view?.adapter = adapter
        refreshlayout.setRefreshHeader(DropBoxHeader(context))
        refreshlayout.setRefreshFooter(ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale))
    }

    override fun initData() {
        viewModel.projects.observe(this) {
            adapter.setNewData(it)
            refreshlayout?.finishRefresh()
            if (viewModel.isHaveMoreArticle) {
                refreshlayout?.finishLoadMore()
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData()
            }
            adapter.setOnItemClickListener { adapter, view, position ->
                val itemBean = it?.get(position)
                itemBean?.let {it->
                    val action = ProjectFragmentDirections.actionToDetailsWebFragment(it.link,it.title,it.author)
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
            viewModel.lazyInit(it.getInt(CID))
        }
    }
}