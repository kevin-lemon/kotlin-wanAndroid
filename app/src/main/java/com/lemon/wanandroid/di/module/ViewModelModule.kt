package com.lemon.wanandroid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lemon.wanandroid.di.ViewModelKey
import com.lemon.wanandroid.factory.CustomViewModelFactory
import com.lemon.wanandroid.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(mainViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: CustomViewModelFactory): ViewModelProvider.Factory
}