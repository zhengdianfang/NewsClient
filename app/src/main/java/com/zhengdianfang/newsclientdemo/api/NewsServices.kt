package com.zhengdianfang.newsclientdemo.api

import com.zhengdianfang.newsclientdemo.model.News
import io.reactivex.Flowable
import retrofit2.http.Query
import retrofit2.http.GET

interface NewsServices {

   @GET("/news")
   fun getNewsList(@Query("category") category: Long?): Flowable<List<News>>
}
