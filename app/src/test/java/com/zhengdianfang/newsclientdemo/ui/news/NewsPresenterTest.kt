package com.zhengdianfang.newsclientdemo.ui.news

import com.nhaarman.mockitokotlin2.verify
import com.zhengdianfang.newsclientdemo.model.News
import com.zhengdianfang.newsclientdemo.repository.NewsRepository
import com.zhengdianfang.newsclientdemo.ui.news.list.INewsContract
import com.zhengdianfang.newsclientdemo.ui.news.list.NewsPresenter
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NewsPresenterTest {

    @Mock
    private lateinit var mockNewRepository: NewsRepository

    @Mock
    private lateinit var mockView: INewsContract.IView

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should execute showNewsList function when receiver news list data`() {
        //given
        val newsPresenter = NewsPresenter()
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            News("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )

        `when`(mockNewRepository.getNewsList(anyLong())).thenReturn(Flowable.just(mockNews))
        ReflectionUtils.refectSetValue(newsPresenter, "newsRepository", mockNewRepository)
        newsPresenter.attach(mockView)
        //when
        newsPresenter.requestNewsList(1)

        //then
        verify(mockView).showNewsList(mockNews)

    }

    @Test
    fun `should execute showEmptyView function when not receiver news list data`() {
        //given
        val newsPresenter = NewsPresenter()
        `when`(mockNewRepository.getNewsList(anyLong())).thenReturn(Flowable.just(emptyList()))
        ReflectionUtils.refectSetValue(newsPresenter, "newsRepository", mockNewRepository)
        newsPresenter.attach(mockView)
        //when
        newsPresenter.requestNewsList(1)

        //then
        verify(mockView).showEmptyView()

    }
}