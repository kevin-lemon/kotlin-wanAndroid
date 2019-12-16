package com.lemon.wanandroid.view

import android.text.TextUtils
import androidx.room.util.StringUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lemon.wanandroid.R
import com.lemon.wanandroid.bean.Article
import com.lemon.wanandroid.utils.format

class HomeArticleAdapter(layoutResId: Int) :
    BaseQuickAdapter<Article.Data, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: Article.Data) {
        val type = item?.type ?: 0
        val isRefresh = item?.fresh ?: false
        var tip: String? = null
        if (type == 1) {
            tip = "置顶"
        } else if (isRefresh) {
            tip = "最新"
        }
        helper?.run {
            item?.run {
                setText(R.id.tv_home_title, title)
                setText(R.id.tv_home_author, author)
                setVisible(R.id.tv_home_author,author.isEmpty())
            setText(
                R.id.tv_home_public_time,
                format(publishTime ?: System.currentTimeMillis())
            )
            setText(R.id.tv_home_category, superChapterName)
            setGone(
                R.id.tv_home_recent,
                (type == 1 || isRefresh) && !TextUtils.isEmpty(tip)
            )   // true 显示，false 隐藏
            setText(R.id.tv_home_recent, tip)
            }
        }
    }
}