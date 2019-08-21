package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.zhengdianfang.newsclientdemo.model.New
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Test


class NewRemoteDataSourceTest {

    private val newsRemoteDataSource = NewRemoteDataSource()

    private val objectMapper = ObjectMapper()

    private val mockServer = MockWebServer()

    @After
    fun tearDown() {
       mockServer.shutdown()
    }

    @Test
    fun `should get new list when request server api`() {
        //given
        val mockNews = listOf(
            New("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", ""),

            New("341", "title2",
                "2019.01.03", 2, "zdf",
                "", "", "", "")
        )

        mockServer.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(objectMapper.writeValueAsString(mockNews)))

        mockServer.start(3000)
        //when
        val testSubscriber = newsRemoteDataSource.getNews().test()

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
            New("123", "title1",
                "2019.01.03", 1, "zdf",
                "", "", "", "")
        )
        mockServer.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(objectMapper.writeValueAsString(mockNews)))

        mockServer.start(3000)
        //when
        val category = 1
        val testSubscriber = newsRemoteDataSource.getNews(category).test()

        //then
        testSubscriber.assertValue { data ->  data.isNotEmpty() && data.size == 1 && data.first().category == 1 }
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
        val testSubscriber = newsRemoteDataSource.getNews().test()

        //then
        testSubscriber.assertNoValues()
        val takeRequest = mockServer.takeRequest()
        assertThat(takeRequest.path, `is`("/news"))
        assertThat(takeRequest.method, `is`("GET"))

    }
}