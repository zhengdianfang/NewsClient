package com.zhengdianfang.newsclientdemo.ui.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhengdianfang.newsclientdemo.model.Category

class NewsViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val categories = mutableListOf<Category>()

    override fun getItem(position: Int): Fragment {
        return NewsPagerFragment.getInstance(categories[position])
    }

    override fun getCount(): Int {
        return categories.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categories[position].title
    }

    fun updateCategories(newData: List<Category>) {
        categories.clear()
        categories.addAll(newData)
        notifyDataSetChanged()
    }


}