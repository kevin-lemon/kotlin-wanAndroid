package com.lemon.wanandroid.di.module

import com.lemon.wanandroid.di.subcomponent.MainActivitySub
import com.lemon.wanandroid.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


//activity绑定组件module示例，未使用
@Module(subcomponents = [MainActivitySub::class])
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindYourAndroidInjectorFactory(factory: MainActivitySub.Factory?): AndroidInjector.Factory<*>?

}