package com.lemon.wanandroid.ui.system
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.SystemMenuAdapter
import com.lemon.wanandroid.bean.SystemMenuBean
import com.lemon.wanandroid.view.flowlayout.FlowAdapter
import com.lemon.wanandroid.view.flowlayout.FlowLayout
import kotlinx.android.synthetic.main.fragment_system.*
import javax.inject.Inject

class SystemFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SystemViewModel by viewModels {
        viewModelFactory
    }

    lateinit var mAdapter: SystemMenuAdapter
    lateinit var flowAdapter: FlowAdapter<SystemMenuBean.Children>
    override fun getContentViewId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        system_menu_view?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        system_menu_view?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = SystemMenuAdapter(R.layout.item_system_menu)
        system_menu_view?.adapter = mAdapter
        val mInflater = LayoutInflater.from(activity)
        flowAdapter = object : FlowAdapter<SystemMenuBean.Children>(){
            override fun getView(parent: FlowLayout?, position: Int, t: Any?): View? {
                val tv = mInflater.inflate(
                    R.layout.item_system_second_menu,
                    parent, false
                ) as TextView
                t as SystemMenuBean.Children
                tv.text = t.name
                return tv
            }
        }
        system_flowLayout.setAdapter(flowAdapter)
        system_flowLayout.setOnItemClickListener(object :FlowLayout.OnItemClickListener{
            override fun onItemClick(view: View?, position: Int){
                val action = SystemFragmentDirections.actionSystemFragmentToSystemDetailsFragment(flowAdapter.getItem(position).id)
                Navigation.findNavController(view!!).navigate(action)
            }
        })
    }

    override fun initData() {
        viewModel.systemMenus.observe(this){
            it.data?.run {
                mAdapter.setNewData(this)
                flowAdapter.mFlowDatas = this[0].children
                flowAdapter.notifyDataChanged()
                mAdapter.setOnItemClickListener { adapter, view, position ->
                    mAdapter.selectPosition(position)
                    val itemBean = this[position]
                    flowAdapter.mFlowDatas = this[position].children
                    flowAdapter.notifyDataChanged()
                }
            }
        }
    }

}