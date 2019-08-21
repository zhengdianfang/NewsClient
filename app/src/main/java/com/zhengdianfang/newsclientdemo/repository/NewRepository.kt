package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable

class NewRepository {
    fun getNews(category: Int?): Flowable<List<New>> {

    }
}