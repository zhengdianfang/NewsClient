package com.zhengdianfang.newsclientdemo.api

import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable
import retrofit2.http.Query
import retrofit2.http.GET

interface NewsServices {

   @GET("/news")
   fun getNews(@Query("category") category: Int?): Flowable<List<New>>
}
