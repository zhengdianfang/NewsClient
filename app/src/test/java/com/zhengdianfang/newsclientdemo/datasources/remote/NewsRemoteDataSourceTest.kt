package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.zhengdianfang.newsclientdemo.model.New
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test


class NewsRemoteDataSourceTest {

    private val newsRemoteDataSource = NewsRemoteDataSource()

    private val objectMapper = ObjectMapper()

    @Test
    fun `should get new list when request server api` () {
        //given
        val mockServer = MockWebServer()
        val mockNews = listOf(
            New("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            New("341", "title2",
                "2019.01.03", 1, "zdf",
                "", "", "", "")
        )
        mockServer.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(objectMapper.writeValueAsString(mockNews)))

        mockServer.start()
        mockServer.url("/news")
        //when
        val testSubscriber = newsRemoteDataSource.getNews().test()

        //then
        testSubscriber.assertValue { data ->  data.isNotEmpty() && data.size == 2 && data.first().title == "title1" }

        mockServer.shutdown()
    }
}