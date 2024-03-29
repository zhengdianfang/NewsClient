package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.zhengdianfang.newsclientdemo.api.ApiClient
import com.zhengdianfang.newsclientdemo.model.News
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Test


class NewsRemoteDataSourceTest {

    private val newsRemoteDataSource = NewsRemoteDataSource(ApiClient.TEST_INSTANCE)

    private val mockServer = MockWebServer()

    @After
    fun tearDown() {
       mockServer.shutdown()
    }

    @Test
    fun `should get new list when request server api`() {
        //given
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            News("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )

        val body = ApiClient.JSON.writeValueAsString(mockNews)
        print(body)
        mockServer.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(body))

        mockServer.start(3000)
        //when
        val testSubscriber = newsRemoteDataSource.getNewsList().test()

        //then
        testSubscriber.assertValue { data ->  data.isNotEmpty() && data.size == 2 && data.first().title == "title1" }
        val takeRequest = mockServer.takeRequest()
        assertThat(takeRequest.path, `is`("/news"))
        assertThat(takeRequest.method, `is`("GET"))

    }

    @Test
    fun `should get new list of category when request server api with category param`() {
        //given
        val mockNews = listOf(
            News("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", "")
        )
        mockServer.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(ApiClient.JSON.writeValueAsString(mockNews)))

        mockServer.start(3000)
        //when
        val category = 1L
        val testSubscriber = newsRemoteDataSource.getNewsList(category).test()

        //then
        testSubscriber.assertValue { data ->  data.isNotEmpty() && data.size == 1 && data.first().category == category }
        val takeRequest = mockServer.takeRequest()
        assertThat(takeRequest.path, `is`("/news?category=1"))
        assertThat(takeRequest.method, `is`("GET"))

    }

    @Test
    fun `should return empty list when request error`() {
        //given
        mockServer.enqueue(MockResponse()
            .setResponseCode(500))

        mockServer.start(3000)
        //when
        val testSubscriber = newsRemoteDataSource.getNewsList().test()

        //then
        testSubscriber.assertErrorMessage("HTTP 500 Server Error")
        val takeRequest = mockServer.takeRequest()
        assertThat(takeRequest.path, `is`("/news"))
        assertThat(takeRequest.method, `is`("GET"))

    }
}