package com.lemon.wanandroid.adapter

import android.text.Html
import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lemon.wanandroid.R
import com.lemon.wanandroid.bean.SystemDataBean
import com.lemon.wanandroid.utils.format

class SystemListAdapter(layoutResId: Int) :
    BaseQuickAdapter<SystemDataBean.Data, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: SystemDataBean.Data?) {
        helper?.run {
            item?.run {
                setText(R.id.tv_system_title, Html.fromHtml(title))
                setText(R.id.tv_system_author, author)
                setGone(R.id.tv_system_author,!TextUtils.isEmpty(author))
                setText(
                    R.id.tv_system_time,
                    format(if(publishTime > 0 ) publishTime else System.currentTimeMillis())
                )
                setText(R.id.tv_system_category, superChapterName)
            }
        }
    }

}