package com.lemon.wanandroid.ui.detailsweb

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R

class DetailsWebActivity :BaseActivity(){


    companion object{
        fun start(activity:FragmentActivity?,bundle: Bundle){
            var intent = Intent(activity,DetailsWebActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_details_web
    }

    override fun initView() {
    }

    override fun initData() {
    }

}