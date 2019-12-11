package com.lemon.wanandroid.di.module

import com.lemon.wanandroid.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity
}