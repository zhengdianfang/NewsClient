package com.zhengdianfang.newsclientdemo.api

import com.zhengdianfang.newsclientdemo.model.Category
import io.reactivex.Flowable
import retrofit2.http.GET

interface CategoryServices {

    @GET("/categories")
    fun getCategories(): Flowable<List<Category>>

}
