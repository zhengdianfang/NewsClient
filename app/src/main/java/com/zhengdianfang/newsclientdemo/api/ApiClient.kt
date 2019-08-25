package com.zhengdianfang.newsclientdemo.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class ApiClient {

    companion object {

        val JSON = ObjectMapper().registerKotlinModule()

        val INSTANCE: Retrofit =
            Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(JacksonConverterFactory.create(JSON))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val TEST_INSTANCE: Retrofit =
            Retrofit.Builder()
                .baseUrl("http://localhost:3000")
                .addConverterFactory(JacksonConverterFactory.create(JSON))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }
}