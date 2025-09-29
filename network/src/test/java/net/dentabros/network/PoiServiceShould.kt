package net.dentabros.network

import junit.framework.Assert
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PoiServiceShould {
    private lateinit var service: PoiService
    private val api: API = mock(API::class.java)
    private val poiResponse: PoiResponse = mock()

    @Test
    fun fetchPlaylistsFromAPI() = runTest {
        service = PoiServiceImp(api)

        service.getPois()

        verify(api, times(1)).getPois()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runTest {
        mockSuccessfulCase()

        org.junit.Assert.assertEquals(Result.success(poiResponse.pois), service.getPois())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runTest {
        mockErrorCase()

        org.junit.Assert.assertEquals(
            "Something went wrong",
            service.getPois().exceptionOrNull()?.message
        )
    }



    private suspend fun mockErrorCase() {
        whenever(api.getPois()).thenThrow(RuntimeException("Something went wrong"))

        service = PoiServiceImp(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getPois()).thenReturn(poiResponse)

        service = PoiServiceImp(api)
    }

}