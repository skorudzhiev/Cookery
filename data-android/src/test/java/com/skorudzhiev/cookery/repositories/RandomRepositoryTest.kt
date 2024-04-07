package com.skorudzhiev.cookery.repositories

import app.cookery.mappers.meal.MealDetailsEntityMapper
import app.cookery.mappers.meal.MealListToMealDetails
import app.cookery.repositories.random.RandomRemoteDataSource
import app.cookery.repositories.random.RandomRemoteDataSourceImpl
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.enqueueResponse
import com.skorudzhiev.cookery.mealDetailEntities
import com.skorudzhiev.cookery.provideTheMealDbTestingApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomRepositoryTest {

    private val sourceFile = "meal-details.json"

    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: RandomRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        dataSource = RandomRemoteDataSourceImpl(
            theMealDbApi = provideTheMealDbTestingApi(mockWebServer = mockWebServer),
            mealDetailsMapper = MealListToMealDetails(mapper = MealDetailsEntityMapper())
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Should fetch meal details correctly given 200 response`() {
        mockWebServer.enqueueResponse(fileName = sourceFile, code = 200)
        runBlocking {
            val actual = dataSource.getRandomMeal().getOrThrow()[0]
            val expected = mealDetailEntities[0]

            assertThat(expected).isNotNull()
            assertThat(actual.mealId).isEqualTo(expected.mealId)
        }
    }

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

    @Test
    fun `Should return an error when the API response is 500`() {
        mockWebServer.enqueueResponse(fileName = sourceFile, code = 500)
        runBlocking {
            runCatching {
                val actual = dataSource.getRandomMeal().getOrThrow()
                val expected = Throwable("")

                assertThat(actual).isEqualTo(expected)
            }
        }
    }
}
