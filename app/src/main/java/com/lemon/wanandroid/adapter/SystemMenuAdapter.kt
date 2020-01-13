package com.lemon.wanandroid.adapter

import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lemon.wanandroid.R
import com.lemon.wanandroid.bean.SystemMenuBean

class SystemMenuAdapter(layoutResId: Int) :BaseQuickAdapter<SystemMenuBean, BaseViewHolder>(layoutResId){
    private var selectPosition: Int = 0
    override fun convert(helper: BaseViewHolder, item: SystemMenuBean?) {
        helper?.run {
            if (layoutPosition == selectPosition){
                setTextColor(R.id.tv_menu, ContextCompat.getColor(mContext, R.color.colorAccent))
                setTypeface(R.id.tv_menu, Typeface.defaultFromStyle(
                    Typeface.BOLD))
            }else{
                setTextColor(R.id.tv_menu, ContextCompat.getColor(mContext, R.color.colorBlack))
                setTypeface(R.id.tv_menu, Typeface.defaultFromStyle(Typeface.NORMAL))
            }
            setText(R.id.tv_menu,item?.name)
        }
    }
    fun selectPosition(position: Int) {
        this.selectPosition = position
        notifyDataSetChanged()
    }

}