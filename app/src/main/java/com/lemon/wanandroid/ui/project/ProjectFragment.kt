package com.lemon.wanandroid.ui.project

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.adapter.ProjectPagerAdapter
import com.lemon.wanandroid.bean.FragmentItem
import com.lemon.wanandroid.ui.projectpage.ProjectPageFragment
import kotlinx.android.synthetic.main.fragment_project.*
import javax.inject.Inject


class ProjectFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ProjectViewModel by viewModels {
        viewModelFactory
    }
    private var fragmentItems = mutableListOf<FragmentItem>()
    private lateinit var adapter: ProjectPagerAdapter
    override fun getContentViewId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        adapter =  ProjectPagerAdapter(childFragmentManager,fragmentItems)
        project_view.adapter = adapter
        project_tabs?.setupWithViewPager(project_view)
    }

    override fun initData() {
        viewModel.projectTabs.observe(this){
            it.data?.run{
                val fragmentItems = mutableListOf<FragmentItem>()
                forEach{projectTab->
                    fragmentItems.add(FragmentItem(projectTab.name,
                        ProjectPageFragment.newInstance(projectTab.id)))
                }
                adapter.setData(fragmentItems)
            }
        }
    }

}