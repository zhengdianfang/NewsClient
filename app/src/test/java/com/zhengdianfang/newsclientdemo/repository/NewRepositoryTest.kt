package com.zhengdianfang.newsclientdemo.repository

import com.zhengdianfang.newsclientdemo.datasources.remote.NewRemoteDataSource
import com.zhengdianfang.newsclientdemo.model.New
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NewRepositoryTest {

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
            New("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            New("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )
        `when`(mockNewRemoteDataSource.getNews()).thenReturn(Flowable.just(mockNews))
        ReflectionUtils.refectSetValue(newRepository, "newRemoteDataSource", mockNewRemoteDataSource)

        //when
        val testSubscriber = newRepository.getNews(null).test()

        //then
        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 2 && data.first().title == "title1"
        }


    }
}