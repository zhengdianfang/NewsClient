package com.zhengdianfang.newsclientdemo.datasources.remote

import com.zhengdianfang.newsclientdemo.api.NewsServices
import com.zhengdianfang.newsclientdemo.model.News
import io.reactivex.Flowable
import retrofit2.Retrofit

class NewsRemoteDataSource(private val client: Retrofit) {

    fun getNewsList(category: Long): Flowable<List<News>> {
        return client.create(NewsServices::class.java)
            .getNewsList(category)
            .onErrorResumeNext(Flowable.just(emptyList()))
    }

    fun getNewsList(): Flowable<List<News>> {
        return client.create(NewsServices::class.java)
            .getNewsList(null)
            .onErrorResumeNext(Flowable.just(emptyList()))
    }
}