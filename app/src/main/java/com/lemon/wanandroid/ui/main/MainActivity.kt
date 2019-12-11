package com.lemon.wanandroid.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by Lemon on 2019/11/29.
 */
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun getContentViewId(): Int {
        return R.layout.activity_main
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

    override fun onResume() {
        super.onResume()
    }
}