package com.lemon.wanandroid.view.flowlayout

import android.view.View

abstract class FlowAdapter<T>{
    var mFlowDatas: List<T>? = null
    var mOnDataChangedListener : OnDataChangedListener? = null

    open fun getCount(): Int {
        mFlowDatas?.let {
            return it.size
        }
        return 0
    }

    open fun getItem(position: Int):T{
            return mFlowDatas?.get(position)!!
    }
    abstract fun getView(parent: FlowLayout?, position: Int, t: Any?): View?

    open fun notifyDataChanged() {
        mOnDataChangedListener?.onChanged()
    }
    interface OnDataChangedListener {
        fun onChanged()
    }

    open fun setOnDataChangedListener(listener: OnDataChangedListener) {
        mOnDataChangedListener = listener
    }
}
