package com.zhengdianfang.newsclientdemo.api

import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable
import retrofit2.http.GET

interface NewsServices {

   @GET("/news")
   fun getNews(): Flowable<List<New>>
}
