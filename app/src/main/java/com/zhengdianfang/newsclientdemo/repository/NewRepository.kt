package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.NewRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable

class NewRepository {

    private val newRemoteDataSource = NewRemoteDataSource()

    fun getNews(category: Int?): Flowable<List<New>> {
        return newRemoteDataSource.getNews()
    }
}