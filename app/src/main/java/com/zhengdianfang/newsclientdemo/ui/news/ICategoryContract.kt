package com.zhengdianfang.newsclientdemo.ui.news

import com.zhengdianfang.newsclientdemo.model.Category
import com.zhengdianfang.newsclientdemo.mvp.IBasePresenter
import com.zhengdianfang.newsclientdemo.mvp.IBaseView

interface ICategoryContract {

    interface IView: IBaseView<IPresenter> {
        fun showCategories(categories: List<Category>)
    }

    interface IPresenter: IBasePresenter<IView> {
        fun requestCategories()
    }

}
