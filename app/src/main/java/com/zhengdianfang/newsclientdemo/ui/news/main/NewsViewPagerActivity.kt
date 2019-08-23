package com.zhengdianfang.newsclientdemo.ui.news.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhengdianfang.newsclientdemo.R
import com.zhengdianfang.newsclientdemo.model.Category
import kotlinx.android.synthetic.main.activity_news_viewpager.*

class NewsViewPagerActivity : AppCompatActivity(),
    ICategoryContract.IView {

    private var categoryPresenter = CategoryPresenter()

    private val viewPagerAdapter =
        NewsViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_viewpager)
        initViews()

        categoryPresenter.attach(this)
        categoryPresenter.requestCategories()
    }

    override fun onDestroy() {
        super.onDestroy()
        categoryPresenter.detach()
    }

    override fun showCategories(categories: List<Category>) {
        viewPagerAdapter.updateCategories(categories)
    }

    private fun initViews() {
        newsViewPager.adapter = viewPagerAdapter
    }
}
