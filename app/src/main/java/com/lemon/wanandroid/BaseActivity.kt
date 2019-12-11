package com.lemon.wanandroid

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by Lemon on 2019/11/29.
 */
abstract class BaseActivity : AppCompatActivity(),HasAndroidInjector{
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
    override fun androidInjector() = androidInjector
}