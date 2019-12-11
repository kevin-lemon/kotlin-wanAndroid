package com.lemon.wanandroid

import android.app.Application
import com.lemon.wanandroid.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WanApp : Application(),HasAndroidInjector{
    @Inject
    lateinit var androidInjector:DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}