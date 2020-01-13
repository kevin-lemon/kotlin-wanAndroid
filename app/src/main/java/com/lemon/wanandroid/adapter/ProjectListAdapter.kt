package com.lemon.wanandroid.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lemon.wanandroid.R
import com.lemon.wanandroid.bean.ProjectBean
import com.lemon.wanandroid.glide.GlideApp
import com.lemon.wanandroid.utils.format

class ProjectListAdapter(layoutResId: Int) :
    BaseQuickAdapter<ProjectBean.Data, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: ProjectBean.Data?) {
        helper?.run {
            item?.run {
                setText(R.id.project_title, title)
                setText(R.id.project_author, author)
                setGone(R.id.project_author, !TextUtils.isEmpty(author))
                setText(
                    R.id.project_time,
                    format(if (publishTime > 0) publishTime else System.currentTimeMillis())
                )
                GlideApp.with(mContext)
                    .load(envelopePic)
                    .placeholder(R.drawable.loading_img)
                    .error(R.drawable.error_img)
                    .into(getView(R.id.project_img))
            }
        }
    }

}