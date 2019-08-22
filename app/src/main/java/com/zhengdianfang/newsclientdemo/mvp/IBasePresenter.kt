package com.zhengdianfang.newsclientdemo.mvp

interface IBasePresenter<V> {
    fun attach(view: V)
    fun detach()
}
