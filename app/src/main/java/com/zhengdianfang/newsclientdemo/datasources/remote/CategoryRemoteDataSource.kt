package com.zhengdianfang.newsclientdemo.datasources.remote

import com.zhengdianfang.newsclientdemo.api.CategoryServices
import com.zhengdianfang.newsclientdemo.model.Category
import io.reactivex.Flowable
import retrofit2.Retrofit

class CategoryRemoteDataSource(private val client: Retrofit) {

    fun getCategories(): Flowable<List<Category>> {
        return client.create(CategoryServices::class.java)
            .getCategories()
    }
}