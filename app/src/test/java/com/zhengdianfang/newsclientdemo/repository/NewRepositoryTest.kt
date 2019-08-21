package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.NewRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.New
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NewRepositoryTest {

    @Mock
    private lateinit var newRemoteDataSource: NewRemoteDataSource

    private val newRepository = NewRepository()

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return new list when get from data source`() {
        //given
        val mockNews = listOf(
            New("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            New("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )
        `when`(newRemoteDataSource.getNews()).thenReturn(Flowable.just(mockNews))

        //when
        val testSubscriber = newRepository.getNews(null).test()

        //then
        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 2 && data.first().title == "title1"
        }

    }
}