package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.zhengdianfang.newsclientdemo.api.CategoryServices
import com.zhengdianfang.newsclientdemo.model.Category
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class CategoryRemoteDataSource {

    private val client by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getCategories(): Flowable<List<Category>> {
        return client.create(CategoryServices::class.java)
            .getCategories()
            .onErrorResumeNext(Flowable.just(emptyList()))
    }
}