package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.NewRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.News
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NewsRepositoryTest {

    @Mock
    private lateinit var mockNewRemoteDataSource: NewRemoteDataSource


    init {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return new list when get from data source`() {
        //given
        val newRepository = NewRepository()
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            News("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )
        `when`(mockNewRemoteDataSource.getNewsList()).thenReturn(Flowable.just(mockNews))
        ReflectionUtils.refectSetValue(newRepository, "newRemoteDataSource", mockNewRemoteDataSource)

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
        val newRepository = NewRepository()
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            News("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )
        `when`(mockNewRemoteDataSource.getNewsList()).thenReturn(Flowable.just(mockNews))
        ReflectionUtils.refectSetValue(newRepository, "newRemoteDataSource", mockNewRemoteDataSource)

        //when
        val testSubscriber = newRepository.getNewsList(1).test()

        //then
        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 1 && data.first().category == 1
        }
    }
}