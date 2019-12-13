package com.lemon.wanandroid.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import com.lemon.wanandroid.ui.home.HomeViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


/**
 * Created by Lemon on 2019/11/29.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val navController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
    }
}