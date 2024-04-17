package com.skorudzhiev.cookery.repositories

import app.cookery.mappers.meal.MealDetailsDtoToDomainMapper
import app.cookery.repositories.search.SearchRemoteDataSource
import app.cookery.repositories.search.SearchRemoteDataSourceImpl
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.enqueueResponse
import com.skorudzhiev.cookery.mealDetailEntities
import com.skorudzhiev.cookery.provideTheMealDbTestingApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest {

    private val sourceFile = "meal-details.json"

    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: SearchRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        dataSource = SearchRemoteDataSourceImpl(
            theMealDbApi = provideTheMealDbTestingApi(mockWebServer = mockWebServer),
            mealDetailsMapper = MealDetailsDtoToDomainMapper()
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
            val actual = dataSource.searchMealByName("Chicken").getOrThrow()[0]
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
                val actual = dataSource.searchMealByName("Chicken").getOrThrow()
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
                val actual = dataSource.searchMealByName("Chicken").getOrThrow()
                val expected = Throwable("")

                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `Should map the response from the API to the domain model`() {
        mockWebServer.enqueueResponse(fileName = sourceFile, code = 200)
        runBlocking {
            val actual = dataSource.searchMealByName("Chicken").getOrThrow()[0]
            val expected = mealDetailEntities[0]

            assertThat(actual).isNotNull()
            assertThat(actual.mealId).isEqualTo(expected.mealId)
            assertThat(actual.mealName).isEqualTo(expected.mealName)
            assertThat(actual.mealCategory).isEqualTo(expected.mealCategory)
            assertThat(actual.mealArea).isEqualTo(expected.mealArea)
            assertThat(actual.cookingInstructions).isEqualTo(expected.cookingInstructions)
            assertThat(actual.mealImage).isEqualTo(expected.mealImage)
            assertThat(actual.mealYoutube).isEqualTo(expected.mealYoutube)
            assertThat(actual.mealTags).isEqualTo(expected.mealTags)
            assertThat(actual.mealIngredient1).isEqualTo(expected.mealIngredient1)
            assertThat(actual.mealMeasure1).isEqualTo(expected.mealMeasure1)
        }
    }
}
