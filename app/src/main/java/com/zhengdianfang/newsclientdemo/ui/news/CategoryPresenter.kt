package com.zhengdianfang.newsclientdemo.ui.news

import com.zhengdianfang.newsclientdemo.repository.CategoryRepository

class CategoryPresenter: ICategoryContract.IPresenter {

    private var view: ICategoryContract.IView? = null

    private val categoryRepository = CategoryRepository()

    override fun attach(view: ICategoryContract.IView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun requestCategories() {
        view?.let {
            categoryRepository.getCategories()
                .subscribe({data ->
                    it.showCategories(data)
                }, {error ->
                    error.printStackTrace()
                })
        }
    }
}