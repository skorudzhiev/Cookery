package com.skorudzhiev.cookery.repositories

import app.cookery.mappers.MealListToMealDetails
import app.cookery.mappers.MealToMealDetails
import app.cookery.repositories.details.remote.MealRemoteDataSourceImpl
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
    private lateinit var dataSource: MealRemoteDataSourceImpl
    private val sourceFile = "meal-details.json"
    private val mealId = "52772"

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        dataSource = MealRemoteDataSourceImpl(
            provideTheMealDbTestingApi(mockWebServer),
            MealListToMealDetails(MealToMealDetails())
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
            val actual = dataSource.getMealDetails(mealId).getOrThrow()[0]
            val expected = mealDetails[0]

            assertThat(expected).isNotNull()
            assertThat(actual.mealId).isEqualTo(expected.mealId)
            assertThat(actual.mealName).isEqualTo(expected.mealName)
            assertThat(actual.cookingInstructions).isEqualTo(expected.cookingInstructions)
        }
    }
}
