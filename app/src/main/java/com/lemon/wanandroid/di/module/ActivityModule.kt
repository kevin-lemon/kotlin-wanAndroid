package com.lemon.wanandroid.di.module

import com.lemon.wanandroid.ui.detailsweb.DetailsWebFragment
import com.lemon.wanandroid.ui.main.MainActivity
import com.lemon.wanandroid.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivityAndroidInjector(): SplashActivity
}