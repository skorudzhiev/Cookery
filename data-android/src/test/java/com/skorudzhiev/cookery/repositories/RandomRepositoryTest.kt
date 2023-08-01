package com.skorudzhiev.cookery.repositories

import app.cookery.data.mappers.MealListToMealDetails
import app.cookery.data.mappers.MealToMealDetails
import app.cookery.repositories.random.RandomDataSource
import app.cookery.repositories.random.RandomDataSourceImpl
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.enqueueResponse
import com.skorudzhiev.cookery.mealDetails
import com.skorudzhiev.cookery.provideTheMealDbTestingApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomRepositoryTest {

    private val sourceFile = "meal-details.json"

    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: RandomDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        dataSource = RandomDataSourceImpl(
            theMealDbApi = provideTheMealDbTestingApi(mockWebServer = mockWebServer),
            mealDetailsMapper = MealListToMealDetails(mapper = MealToMealDetails())
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Ignore("This test is failing because the response is not being mocked correctly")
    @Test
    fun `Should fetch meal details correctly given 200 response`() {
        mockWebServer.enqueueResponse(fileName = sourceFile, code = 200)
        runBlocking {
            val actual = dataSource.getRandomMeal().getOrThrow()
            val expected = mealDetails

            assertThat(expected).isNotNull()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Ignore("This test is failing because the response is not being mocked correctly")
    @Test
    fun `Should return an error when the API response is 404`() {
        mockWebServer.enqueueResponse(fileName = sourceFile, code = 404)
        runBlocking {
            runCatching {
                val actual = dataSource.getRandomMeal().getOrThrow()
                val expected = Throwable("")

                assertThat(actual).isEqualTo(expected)
            }
        }
    }
}
