package com.lemon.wanandroid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lemon.wanandroid.di.ViewModelKey
import com.lemon.wanandroid.factory.CustomViewModelFactory
import com.lemon.wanandroid.ui.article.ArticleViewModel
import com.lemon.wanandroid.ui.articlepage.ArticlePageViewModel
import com.lemon.wanandroid.ui.home.HomeViewModel
import com.lemon.wanandroid.ui.project.ProjectViewModel
import com.lemon.wanandroid.ui.projectpage.ProjectPageViewModel
import com.lemon.wanandroid.ui.system.SystemViewModel
import com.lemon.wanandroid.ui.systemdetails.SystemDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectViewModel::class)
    abstract fun bindProjectViewModel(projectViewModel: ProjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectPageViewModel::class)
    abstract fun bindProjectPageViewModel(projectPageViewModel: ProjectPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleViewModel(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticlePageViewModel::class)
    abstract fun bindArticlePageViewModel(articleListViewModel: ArticlePageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SystemViewModel::class)
    abstract fun bindSystemViewModel(systemViewModel: SystemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SystemDetailsViewModel::class)
    abstract fun bindSystemDetailsViewModel(systemDetailsViewModel: SystemDetailsViewModel): ViewModel
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: CustomViewModelFactory): ViewModelProvider.Factory
}