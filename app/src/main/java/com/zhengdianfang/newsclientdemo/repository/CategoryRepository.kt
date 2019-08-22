package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.CategoryRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.Category
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryRepository {

    private val categoryRemoteDataSource = CategoryRemoteDataSource()

    fun getCategories(): Flowable<List<Category>> {
        return categoryRemoteDataSource .getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}