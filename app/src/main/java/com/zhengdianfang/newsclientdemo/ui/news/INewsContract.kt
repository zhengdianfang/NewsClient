package com.zhengdianfang.newsclientdemo.ui.news

import com.zhengdianfang.newsclientdemo.model.News
import com.zhengdianfang.newsclientdemo.mvp.IBasePresenter
import com.zhengdianfang.newsclientdemo.mvp.IBaseView

interface INewsContract {
    interface IView: IBaseView<Persenter> {
        fun showNewsList(newsList: List<News>)
        fun showEmptyView()
    }

    interface Persenter: IBasePresenter<IView> {
        fun requestNewsList(category: Int)
    }
}