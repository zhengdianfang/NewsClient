package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.NewsRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.News
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import com.zhengdianfang.newsclientdemo.utils.RxImmediateSchedulerRule
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.After
import org.junit.ClassRule


class NewsRepositoryTest {

    @Mock
    private lateinit var mockNewsRemoteDataSource: NewsRemoteDataSource

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return new list when get from data source`() {
        //given
        val newRepository = NewsRepository()
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            News("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )
        `when`(mockNewsRemoteDataSource.getNewsList()).thenReturn(Flowable.just(mockNews))
        ReflectionUtils.refectSetValue(newRepository, "newRemoteDataSource", mockNewsRemoteDataSource)

        //when
        val testSubscriber = newRepository.getNewsList(null).test()

        //then
        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 2 && data.first().title == "title1"
        }


    }

    @Test
    fun `should return  new list of specified category when get form data source`() {
        //given
        val newRepository = NewsRepository()
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            News("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )
        `when`(mockNewsRemoteDataSource.getNewsList()).thenReturn(Flowable.just(mockNews))
        ReflectionUtils.refectSetValue(newRepository, "newRemoteDataSource", mockNewsRemoteDataSource)

        //when
        val testSubscriber = newRepository.getNewsList(1).test()

        //then
        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 1 && data.first().category == 1
        }
    }
}