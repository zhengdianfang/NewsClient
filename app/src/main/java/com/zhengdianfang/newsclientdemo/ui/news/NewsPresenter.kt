package com.zhengdianfang.newsclientdemo.ui.news

import com.zhengdianfang.newsclientdemo.repository.NewsRepository

class NewsPresenter: INewsContract.Presenter {

    private var view: INewsContract.IView? = null

    private val newsRepository = NewsRepository()

    override fun requestNewsList(category: Long) {
        view?.let {
           newsRepository.getNewsList(category)
               .subscribe({data ->
                   if (data.isEmpty()) {
                       it.showEmptyView()
                   } else {
                       it.showNewsList(data)
                   }
               }, { error ->
                   error.printStackTrace()
                   it.showEmptyView()
               })
        }
    }

    override fun attach(view: INewsContract.IView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}