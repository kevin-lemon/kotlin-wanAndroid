package com.lemon.wanandroid.adapter

import android.text.Html
import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lemon.wanandroid.R
import com.lemon.wanandroid.bean.PublicArticleDataBean
import com.lemon.wanandroid.utils.format

class ArticleListAdapter(layoutResId: Int) :
    BaseQuickAdapter<PublicArticleDataBean.Data, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: PublicArticleDataBean.Data?) {
        helper?.run {
            item?.run {
                setText(R.id.tv_article_title, Html.fromHtml(title))
                setText(R.id.tv_article_author, author)
                setGone(R.id.tv_article_author,!TextUtils.isEmpty(author))
                setText(
                    R.id.tv_article_public_time,
                    format(if(publishTime > 0 ) publishTime else System.currentTimeMillis())
                )
                setText(R.id.tv_article_category, superChapterName)
            }
        }
    }

}