package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.CategoryRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.Category
import io.reactivex.Flowable

class CategoryRepository {

    private val categoryRemoteDataSource = CategoryRemoteDataSource()

    fun getCategories(): Flowable<List<Category>> {
        return categoryRemoteDataSource.getCategories()
    }

}