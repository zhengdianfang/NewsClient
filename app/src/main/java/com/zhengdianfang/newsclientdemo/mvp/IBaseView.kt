package com.zhengdianfang.newsclientdemo.mvp

interface IBaseView<P> {
    fun setPresenter(presenter: P)
}
