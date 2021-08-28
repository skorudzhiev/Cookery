package com.skorudzhiev.cookery.repositories

import app.cookery.repositories.details.TheMealDbMealDataSource
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.enqueueResponse
import com.skorudzhiev.cookery.mealDetails
import com.skorudzhiev.cookery.provideTheMealDbTestingApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MealRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: TheMealDbMealDataSource
    private val sourceFile = "meal-details.json"
    private val mealId = "52772"

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        dataSource = TheMealDbMealDataSource(
            provideTheMealDbTestingApi(mockWebServer)
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch meal details correctly given 200 response`() {
        mockWebServer.enqueueResponse(sourceFile, 200)
        runBlocking {
            val actual = dataSource.getMealDetails(mealId).getOrThrow()
            val expected = mealDetails

            assertThat(expected).isNotNull()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
