package com.lemon.wanandroid.UI.main

import androidx.activity.viewModels
import androidx.lifecycle.*
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import com.lemon.wanandroid.bean.User
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Lemon on 2019/11/29.
 */
class MainActivity : BaseActivity(){
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            SavedStateViewModelFactory(application,this)
        }
    )
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        viewModel.user.observe(this,
            Observer<User> {
                name.text = it.name
            })
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
    }
}