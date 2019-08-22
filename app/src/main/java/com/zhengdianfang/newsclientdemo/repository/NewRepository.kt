package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.NewRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.News
import io.reactivex.Flowable

class NewRepository {

    private val newRemoteDataSource = NewRemoteDataSource()

    fun getNewsList(category: Int?): Flowable<List<News>> {
        return newRemoteDataSource.getNewsList()
            .concatMap{ data -> Flowable.fromIterable(data) }
            .filter { item -> category == null || item.category == category }
            .toList()
            .toFlowable()
    }
}