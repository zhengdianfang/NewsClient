package com.zhengdianfang.newsclientdemo.ui.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.zhengdianfang.newsclientdemo.ui.news.main.NewsViewPagerActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsViewPagerActivityTest {

    @get: Rule
    val activityRule = ActivityTestRule(NewsViewPagerActivity::class.java)

    @Test
    fun should_display_tab_with_category() {
        onView(withText("头条")).check(matches(isDisplayed()))
        onView(withText("体育")).check(matches(isDisplayed()))
        onView(withText("娱乐")).check(matches(isDisplayed()))
        onView(withText("科技")).check(matches(isDisplayed()))
    }

    @Test
    fun should_display_news_item_with_category_1() {
        hasEntry(equalTo("STR"), `is`("王中王！德约一稳定江山，大满贯稳步追赶或成最终赢家"))
    }
}