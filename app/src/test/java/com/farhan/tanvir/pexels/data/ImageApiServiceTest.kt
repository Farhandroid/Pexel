/*
 * Created by Farhan Tanvir on 12/5/21, 6:32 PM
 * Last modified 12/5/21, 6:32 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.data

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ImageApiServiceTest {
    private lateinit var service: ImageApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))//We will use MockWebServers url
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApiService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @Test
    fun getSearchedResult_sentRequest_receivedExpected() {
        runBlocking {
            // Prepare fake response
            enqueueMockResponse("ImageResponse.json")
            //Send Request to the MockServer
            val responseBody = service.getSearchedImage("nature", 5).body()
            //Request received by the mock server
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v1/search?query=nature&per_page=5")
        }
    }

    @Test
    fun getSearchedResult_receivedResponse_wrongPageSize_shouldFail() {
        runBlocking {
            enqueueMockResponse("ImageResponse.json")
            val responseBody = service.getSearchedImage("nature", 5).body()
            val photoList = responseBody!!.photos
            assertThat(photoList.size).isEqualTo(6)
        }
    }

    @Test
    fun getSearchedResult_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("ImageResponse.json")
            val responseBody = service.getSearchedImage("nature", 5).body()
            val photoList = responseBody!!.photos
            val photo = photoList[0]
            assertThat(photo.id).isEqualTo(572897)
            assertThat(photo.photographer).isEqualTo("eberhard grossgasteiger")
            assertThat(photo.photographer_url).isEqualTo("https://www.pexels.com/@eberhardgross")
        }
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}