package com.lemon.wanandroid.ui.projectlist

import android.os.Bundle
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R

private const val CID = "cid"
class ProjectListFragment : BaseFragment(){

    companion object{
        fun newInstance(cid:Int) : ProjectListFragment {
            return ProjectListFragment().apply {
                arguments = Bundle().apply {
                    putInt(CID,cid)
                }
            }
        }
    }
    override fun getContentViewId(): Int {
        return R.layout.fragment_project_list
    }

    override fun initView() {
        arguments?.let {

        }
    }

    override fun initData() {

    }

}