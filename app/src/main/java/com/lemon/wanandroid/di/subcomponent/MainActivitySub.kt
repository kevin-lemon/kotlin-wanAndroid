package com.lemon.wanandroid.di.subcomponent

import com.lemon.wanandroid.di.module.ViewModelModule
import com.lemon.wanandroid.ui.main.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

//activity绑定组件module示例，未使用
@Subcomponent(modules = [])
interface MainActivitySub : AndroidInjector<MainActivity>{
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>{}
}