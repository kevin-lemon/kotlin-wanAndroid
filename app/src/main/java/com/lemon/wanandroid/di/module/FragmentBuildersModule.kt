package com.lemon.wanandroid.di.module

import com.lemon.wanandroid.ui.home.HomeFragment
import com.lemon.wanandroid.ui.project.ProjectFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment
}