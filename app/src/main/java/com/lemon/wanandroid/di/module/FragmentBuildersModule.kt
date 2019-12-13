package com.lemon.wanandroid.di.module

import com.lemon.wanandroid.ui.article.ArticleFragment
import com.lemon.wanandroid.ui.home.HomeFragment
import com.lemon.wanandroid.ui.navigation.NavigationFragment
import com.lemon.wanandroid.ui.project.ProjectFragment
import com.lemon.wanandroid.ui.system.SystemFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleFragment(): ArticleFragment

    @ContributesAndroidInjector
    abstract fun contributeSystemFragment(): SystemFragment

    @ContributesAndroidInjector
    abstract fun contributeNavigationFragment(): NavigationFragment
}