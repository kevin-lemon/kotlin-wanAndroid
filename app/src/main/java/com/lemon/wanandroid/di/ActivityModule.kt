package com.lemon.wanandroid.di

import com.lemon.wanandroid.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity
}