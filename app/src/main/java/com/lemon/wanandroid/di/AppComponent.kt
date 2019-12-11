package com.lemon.wanandroid.di

import com.lemon.wanandroid.WanApp
import com.lemon.wanandroid.di.module.ActivityModule
import com.lemon.wanandroid.di.module.AppModule
import com.lemon.wanandroid.di.module.MainActivityModule
import com.lemon.wanandroid.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class
        ]
)
interface AppComponent {
    fun inject(app : WanApp)
}