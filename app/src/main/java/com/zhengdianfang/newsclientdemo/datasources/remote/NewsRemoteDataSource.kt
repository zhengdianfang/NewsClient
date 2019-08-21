package com.zhengdianfang.newsclientdemo.datasources.remote

import com.zhengdianfang.newsclientdemo.api.NewsServices
import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class NewsRemoteDataSource {
    private val client by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    fun getNews(): Flowable<List<New>> {
        return client.create(NewsServices::class.java).getNews()

    }
}