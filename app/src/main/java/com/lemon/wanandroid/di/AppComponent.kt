package com.lemon.wanandroid.di

import com.lemon.wanandroid.WanApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        AppModule::class]
)
interface AppComponent {
    fun inject(app : WanApp)
}