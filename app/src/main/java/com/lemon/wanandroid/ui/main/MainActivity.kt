package com.lemon.wanandroid.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import com.lemon.wanandroid.setupWithNavController
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Lemon on 2019/11/29.
 */
class MainActivity : BaseActivity() {
    private var currentNavController: LiveData<NavController>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setupBottomNavigationBar()
    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(R.navigation.home,R.navigation.project,R.navigation.article,R.navigation.system,R.navigation.nagivation)

        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}