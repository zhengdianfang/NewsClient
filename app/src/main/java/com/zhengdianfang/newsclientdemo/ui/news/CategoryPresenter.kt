package com.zhengdianfang.newsclientdemo.ui.news

class CategoryPresenter: ICategoryContract.IPresenter {

    private var view: ICategoryContract.IView? = null

    override fun attach(view: ICategoryContract.IView) {
        this.view = this.view
    }

    override fun detach() {
        this.view = null
    }

    override fun requestCategories() {
    }
}