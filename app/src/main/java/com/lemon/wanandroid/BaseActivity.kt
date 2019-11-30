package com.lemon.wanandroid

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Lemon on 2019/11/29.
 */
abstract class BaseActivity : AppCompatActivity(){
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        mContext = this
        initView()
        initData()
    }

    abstract fun getContentViewId():Int
    abstract fun initView()
    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
    }
}