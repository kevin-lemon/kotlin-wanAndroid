package com.lemon.wanandroid.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lemon.wanandroid.bean.FragmentItem


class ArticlePageAdapter(fm: FragmentManager, var fragmentItems: List<FragmentItem>) :FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
){

    override fun getItem(position: Int): Fragment {
        return fragmentItems[position].fragment
    }

    override fun getCount(): Int {
        return fragmentItems.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentItems[position].name
    }

    fun setData(list: List<FragmentItem>){
        fragmentItems = list
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }
}