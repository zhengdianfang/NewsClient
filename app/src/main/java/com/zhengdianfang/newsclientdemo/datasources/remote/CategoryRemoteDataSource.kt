package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.zhengdianfang.newsclientdemo.api.NewsServices
import com.zhengdianfang.newsclientdemo.model.Category
import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class CategoryRemoteDataSource {

    private val client by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getCategories(): Flowable<List<Category>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}