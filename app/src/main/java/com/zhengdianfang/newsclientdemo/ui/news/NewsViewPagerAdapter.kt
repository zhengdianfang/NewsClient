package com.zhengdianfang.newsclientdemo.ui.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhengdianfang.newsclientdemo.model.Category

class NewsViewPagerAdapter(fm: FragmentManager?, private val categories: List<Category>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return NewsPagerFragment.getInstance(categories[position])
    }

    override fun getCount(): Int {
        return categories.count()
    }
}