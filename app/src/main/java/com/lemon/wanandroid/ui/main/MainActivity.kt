package com.lemon.wanandroid.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import com.lemon.wanandroid.repository.HomeRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        button2.setOnClickListener {
                initData()
        }
    }

    override fun initData() {
        viewModel.banner.observe(this){
            Log.d("main", "res:$it")
        }
    }

    override fun onResume() {
        super.onResume()
    }
}