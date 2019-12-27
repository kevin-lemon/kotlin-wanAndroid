package com.lemon.wanandroid

import android.app.Application
import android.content.Context
import com.lemon.wanandroid.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WanApp : Application(),HasAndroidInjector{
    @Inject
    lateinit var androidInjector:DispatchingAndroidInjector<Any>

    companion object{
        var context : Application? = null
        fun getContext():Context{
            return context!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        DaggerAppComponent.create().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}