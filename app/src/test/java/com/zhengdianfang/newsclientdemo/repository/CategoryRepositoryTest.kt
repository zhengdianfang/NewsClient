package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.CategoryRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.Category
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CategoryRepositoryTest {

    @Mock
    private lateinit var mockCategoryRemoteDataSource: CategoryRemoteDataSource


    init {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return category list when get from data source`() {
        //given
        val categoryRepository = CategoryRepository()
        val mockCategories = listOf(
            Category(1, "category1", 1),
            Category(2, "category2", 2)
        )
        `when`(mockCategoryRemoteDataSource.getCategories()).thenReturn(Flowable.just(mockCategories))
        ReflectionUtils.refectSetValue(categoryRepository, "categoryRemoteDataSource", mockCategoryRemoteDataSource)
        //when
        val testSubscriber = categoryRepository.getCategories().test()
        //then

        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 2 && data.first().title == "category1"
        }
    }
}