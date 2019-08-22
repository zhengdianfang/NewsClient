package com.zhengdianfang.newsclientdemo.datasources.remote

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.zhengdianfang.newsclientdemo.api.ApiClient
import com.zhengdianfang.newsclientdemo.model.Category
import com.zhengdianfang.newsclientdemo.utils.ReflectionUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class CategoryRemoteDataSourceTest {

    private val mockWebServer = MockWebServer()

    private val objectMapper = ObjectMapper()

    private val categoryRemoteDataSource = CategoryRemoteDataSource(ApiClient.TEST_INSTANCE)

    private val mockCategories = listOf(
        Category(1, "category1", 1),
        Category(2, "category2", 2)
    )

    @Before
    fun setUp() {
        val mockClient = Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        ReflectionUtils.refectSetValue(categoryRemoteDataSource, "client", mockClient)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should return categories when request category api` () {
        //given
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(mockCategories))
        )
        mockWebServer.start(3000)

        //when
        val testSubscriber = categoryRemoteDataSource.getCategories().test()

        //then
        testSubscriber.assertValue { data ->
            data.isNotEmpty() && data.size == 2 && data.first().title == "category1"
        }
        val takeRequest = mockWebServer.takeRequest()
        assertThat(takeRequest.path, `is`("/categories"))
        assertThat(takeRequest.method, `is`("GET"))
    }

    @Test
    fun `should return empty list when request error` () {
        //given
        mockWebServer.enqueue(
            MockResponse().setResponseCode(500)
        )
        mockWebServer.start(3000)

        //when
        val testSubscriber = categoryRemoteDataSource.getCategories().test()

        //then
        testSubscriber.assertValue { data -> data.isEmpty() }
        val takeRequest = mockWebServer.takeRequest()
        assertThat(takeRequest.path, `is`("/categories"))
        assertThat(takeRequest.method, `is`("GET"))
    }
}