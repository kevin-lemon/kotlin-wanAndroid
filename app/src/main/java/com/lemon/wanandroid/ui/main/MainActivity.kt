package com.lemon.wanandroid.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import com.lemon.wanandroid.setupWithNavController
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


/**
 * Created by Lemon on 2019/11/29.
 */
class MainActivity : BaseActivity() {
    private var currentNavController: LiveData<NavController>? = null

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
        val navGraphIds = listOf(R.navigation.home,R.navigation.project,R.navigation.system,R.navigation.article,R.navigation.nagivation)

        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.currentDestination?.label?.equals("homeFragment") == true){
            moveTaskToBack(true)
            return
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}