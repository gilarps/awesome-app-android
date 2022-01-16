package com.gilar.awesomeapp

import com.gilar.awesomeapp.data.PhotosData
import com.gilar.awesomeapp.data.remote.PexelsService
import com.gilar.awesomeapp.util.FileReaderUtil
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class PexelsServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var pexelsService: PexelsService

    private fun setUpRetrofitService() {
        pexelsService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(PexelsService::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("BASE_URL${request.path}")
            return when (request.path) {
                "/curated?page=1&per_page=16" -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReaderUtil.kotlinReadFileWithNewLineFromResources("photos.json"))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        mockWebServer.start()
        setUpRetrofitService()
    }

    @Throws(IOException::class)
    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Assert get photos response structure match JSON Server response`() = runBlocking {
        // This shouldn't have to throw an error if the response
        // is well mapped with the server response mocked in [setUpMockWebServerDispatcher]
        val photos = pexelsService.fetchPhotos(
            page = 1,
            perPage = 16
        )

        assertEquals(
            "Photos size match with the one provided in resources",
            PhotosData.provideRemotePhotosFromAssets().size,
            photos.photos.size
        )
    }

    @Test
    fun `Assert get photos response value is valid`() = runBlocking {
        val photos = pexelsService.fetchPhotos(
            page = 1,
            perPage = 16
        )

        MatcherAssert.assertThat(photos.photos[0].id, CoreMatchers.`is`(10824761))
        MatcherAssert.assertThat(photos.photos[0].photographer, CoreMatchers.`is`("Lisa Fotios"))
        MatcherAssert.assertThat(
            photos.photos[0].src?.original,
            CoreMatchers.`is`("https://images.pexels.com/photos/10824761/pexels-photo-10824761.jpeg")
        )
    }
}