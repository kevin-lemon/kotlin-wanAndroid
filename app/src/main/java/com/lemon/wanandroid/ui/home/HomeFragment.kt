package com.lemon.wanandroid.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import javax.inject.Inject

class HomeFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels{
        viewModelFactory
    }

    override fun getContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
    }

    override fun initData() {
        viewModel.banner.observe(this){
            Log.d("main", "res:$it")
        }
        viewModel.article.observe(this){
            Log.d("main", "article res:$it")
        }
    }

}