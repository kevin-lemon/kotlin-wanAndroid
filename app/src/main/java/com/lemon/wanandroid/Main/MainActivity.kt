package com.lemon.wanandroid.Main

import androidx.activity.viewModels
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.bean.User

/**
 * Created by wxk on 2019/11/29.
 */
class MainActivity : BaseActivity(){
    // UserProfileFragment
    private val viewModel:MainViewModel by viewModels(
        factoryProducer = {
            SavedStateViewModelFactory(application,this)
        }
    )
    override fun getContentViewId(): Int {
        TODO()
    }

    override fun initView() {
        viewModel.user.observe(this,
            Observer<User> {
                it.name
            })
    }

    override fun initData() {
    }

}