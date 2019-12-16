package com.lemon.wanandroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment : Fragment() {

    private var mContentView : View? = null
    private var isAlreadyView : Boolean = false
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mContentView == null){
            mContentView  = inflater.inflate(getContentViewId(), container, false)
            isAlreadyView = false
        }
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isAlreadyView){
            initView()
            initData()
            isAlreadyView = true
        }
    }
    abstract fun getContentViewId():Int
    abstract fun initView()
    abstract fun initData()
}