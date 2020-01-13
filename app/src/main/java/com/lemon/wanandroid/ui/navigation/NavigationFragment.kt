package com.lemon.wanandroid.ui.navigation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.NavMenuAdapter
import com.lemon.wanandroid.bean.NavBean
import com.lemon.wanandroid.view.flowlayout.FlowAdapter
import com.lemon.wanandroid.view.flowlayout.FlowLayout
import kotlinx.android.synthetic.main.fragment_nagivation.*
import java.util.*
import javax.inject.Inject

class NavigationFragment : BaseFragment(){


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NavigationViewModel by viewModels {
        viewModelFactory
    }

    lateinit var mAdapter: NavMenuAdapter
    lateinit var flowAdapter: FlowAdapter<NavBean.NavArticle>
    override fun getContentViewId(): Int {
        return R.layout.fragment_nagivation
    }

    override fun initView() {
        nav_menu_view?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        nav_menu_view?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = NavMenuAdapter(R.layout.item_nav_menu)
        nav_menu_view?.adapter = mAdapter
        val mInflater = LayoutInflater.from(activity)
        flowAdapter = object : FlowAdapter<NavBean.NavArticle>(){
            override fun getView(parent: FlowLayout?, position: Int, t: Any?): View? {
                val tv = mInflater.inflate(
                    R.layout.item_system_second_menu,
                    parent, false
                ) as TextView
                t as NavBean.NavArticle
                val random = Random()
                val r: Int = 30 + random.nextInt(200)
                val g: Int = 30 + random.nextInt(200)
                val b: Int = 30 + random.nextInt(200)
                tv.setTextColor(Color.rgb(r, g, b))
                tv.text = t.title
                return tv
            }

        }
        nav_flowLayout.setAdapter(flowAdapter)
        nav_flowLayout.setOnItemClickListener(object :FlowLayout.OnItemClickListener{
            override fun onItemClick(view: View?, position: Int){
                flowAdapter.getItem(position)?.run {
                    val action = NavigationFragmentDirections.actionToDetailsWebFragment(link,title,author)
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
        })
    }

    override fun initData() {
        viewModel.navDatas.observe(this){
            it.data?.run {
                mAdapter.setNewData(this)
                flowAdapter.mFlowDatas = this[0].articles
                flowAdapter.notifyDataChanged()
                mAdapter.setOnItemClickListener { adapter, view, position ->
                    mAdapter.selectPosition(position)
                    val itemBean = this[position]
                    flowAdapter.mFlowDatas = this[position].articles
                    flowAdapter.notifyDataChanged()
                }
            }
        }

    }

}