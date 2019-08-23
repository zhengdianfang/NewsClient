package com.zhengdianfang.newsclientdemo.ui.news

import com.nhaarman.mockitokotlin2.verify
import com.zhengdianfang.newsclientdemo.model.Category
import com.zhengdianfang.newsclientdemo.repository.CategoryRepository
import com.zhengdianfang.newsclientdemo.ui.news.main.CategoryPresenter
import com.zhengdianfang.newsclientdemo.ui.news.main.ICategoryContract
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CategoryPresenterTest {

    @Mock
    private lateinit var mockCategoryRepository: CategoryRepository

    @Mock
    private lateinit var mockView: ICategoryContract.IView

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should execute showCategories function when receiver categories data from server`() {
        //given
        val categoryPresenter = CategoryPresenter()

        val mockCategories = listOf(
            Category(1, "category1", 1),
            Category(2, "category2", 2)
        )
        `when`(mockCategoryRepository.getCategories()).thenReturn(Flowable.just(mockCategories))
        ReflectionUtils.refectSetValue(categoryPresenter, "categoryRepository", mockCategoryRepository)
        categoryPresenter.attach(mockView)
        //when
        categoryPresenter.requestCategories()
        //then
        verify(mockView).showCategories(mockCategories)


    }
}