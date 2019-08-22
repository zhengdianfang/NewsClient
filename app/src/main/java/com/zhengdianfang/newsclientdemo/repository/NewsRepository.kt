package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.api.ApiClient
import com.zhengdianfang.newsclientdemo.datasources.remote.NewsRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.News
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepository {

    private val newRemoteDataSource = NewsRemoteDataSource(ApiClient.INSTANCE)

    fun getNewsList(category: Long?): Flowable<List<News>> {
        return newRemoteDataSource.getNewsList()
            .concatMap{ data -> Flowable.fromIterable(data) }
            .filter { item -> category == null || item.category == category }
            .toList()
            .toFlowable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}