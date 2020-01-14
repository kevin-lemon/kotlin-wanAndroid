package com.lemon.wanandroid.ui.systemdetails

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.SystemListAdapter
import com.lemon.wanandroid.ui.project.ProjectFragmentDirections
import com.scwang.smartrefresh.header.DropBoxHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.refreshlayout
import kotlinx.android.synthetic.main.fragment_system_details.*
import javax.inject.Inject

class SystemDetailsFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SystemDetailsViewModel by viewModels {
        viewModelFactory
    }
    lateinit var mAdapter : SystemListAdapter
    override fun getContentViewId(): Int {
        return R.layout.fragment_system_details
    }

    override fun initView() {
        refreshlayout?.setEnableRefresh(true)
        refreshlayout?.setReboundDuration(300)
        refreshlayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getSystemDetails(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getSystemDetails(true)
            }

        })
        system_view?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        system_view?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = SystemListAdapter(R.layout.item_system_details)
        system_view?.adapter = mAdapter
        refreshlayout.setRefreshHeader(DropBoxHeader(context))
        refreshlayout.setRefreshFooter(ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale))
    }

    override fun initData() {
        viewModel.systems.observe(this) {
            mAdapter.setNewData(it)
            refreshlayout?.finishRefresh()
            if (viewModel.isHaveMoreSystem) {
                refreshlayout?.finishLoadMore()
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData()
            }
            mAdapter.setOnItemClickListener { adapter, view, position ->
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
            viewModel.lazyInit(SystemDetailsFragmentArgs.fromBundle(it).id)
        }
    }

}