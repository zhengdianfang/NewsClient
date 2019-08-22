package com.zhengdianfang.newsclientdemo.ui.news

import com.zhengdianfang.newsclientdemo.model.News
import com.zhengdianfang.newsclientdemo.mvp.IBasePresenter
import com.zhengdianfang.newsclientdemo.mvp.IBaseView

interface INewsContract {
    interface IView: IBaseView<Presenter> {
        fun showNewsList(newsList: List<News>)
        fun showEmptyView()
    }

    interface Presenter: IBasePresenter<IView> {
        fun requestNewsList(category: Long)
    }
}