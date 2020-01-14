package com.lemon.wanandroid.ui.splash

import android.content.Intent
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.lemon.wanandroid.BaseActivity
import com.lemon.wanandroid.R
import com.lemon.wanandroid.ui.main.MainActivity

/**
 * Created by Lemon on 2019/11/29.
 */
 class SplashActivity : BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
    }

    override fun initData() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },3000)
    }

    private fun startAnimation(
        mLottieAnimationView: LottieAnimationView,
        animationName: String
    ) {
        mLottieAnimationView.setAnimation(animationName)
        mLottieAnimationView.playAnimation()
    }

    private fun cancelAnimation(mLottieAnimationView: LottieAnimationView?) {
        mLottieAnimationView?.cancelAnimation()
    }
}