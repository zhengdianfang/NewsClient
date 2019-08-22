package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.zhengdianfang.newsclientdemo.api.NewsServices
import com.zhengdianfang.newsclientdemo.model.News
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class NewRemoteDataSource {
    private val client by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getNewsList(category: Int): Flowable<List<News>> {
        return client.create(NewsServices::class.java)
            .getNewsList(category)
            .onErrorResumeNext(Flowable.empty())
    }

    fun getNewsList(): Flowable<List<News>> {
        return client.create(NewsServices::class.java)
            .getNewsList(null)
            .onErrorResumeNext(Flowable.empty())
    }
}