package com.skorudzhiev.cookery.repositories

import app.cookery.mappers.area.AreaEntityMapper
import app.cookery.mappers.area.AreasToArea
import app.cookery.mappers.category.CategoriesToCategory
import app.cookery.mappers.category.CategoryDetailsEntityMapper
import app.cookery.mappers.category.MealCategoryToCategory
import app.cookery.mappers.category.MealsToCategoryDetails
import app.cookery.repositories.categories.remote.CategoriesRemoteDataSourceImpl
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.allMealAreaEntities
import com.skorudzhiev.cookery.allMealCategories
import com.skorudzhiev.cookery.enqueueResponse
import com.skorudzhiev.cookery.mealsFilteredByArea
import com.skorudzhiev.cookery.mealsFilteredByCategory
import com.skorudzhiev.cookery.provideTheMealDbTestingApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoriesRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: CategoriesRemoteDataSourceImpl

    private val allMealsCategoriesSourceFile = "all-meal-categories.json"
    private val allMealAreasSourceFile = "all-meal-areas.json"
    private val mealsFilteredByAreaSourceFile = "meals-filtered-by-area.json"
    private val mealsFilteredByCategorySourceFile = "meals-filtered-by-category.json"
    private val mealArea = "Canadian"
    private val mealCategory = "Seafood"

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        dataSource = CategoriesRemoteDataSourceImpl(
            provideTheMealDbTestingApi(mockWebServer),
            AreasToArea(mapper = AreaEntityMapper()),
            CategoriesToCategory(MealCategoryToCategory()),
            MealsToCategoryDetails(CategoryDetailsEntityMapper())
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch all meal categories given response 200`() {
        mockWebServer.enqueueResponse(allMealsCategoriesSourceFile, 200)
        runBlocking {
            val actual = dataSource.getAllMealCategories().getOrThrow()
            val expected = allMealCategories

            assertThat(expected).isNotNull()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `should fetch all meal areas given response 200`() {
        mockWebServer.enqueueResponse(allMealAreasSourceFile, 200)
        runBlocking {
            val actual = dataSource.getMealAreas().getOrThrow()
            val expected = allMealAreaEntities

            assertThat(expected).isNotNull()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `should fetch meals filtered by area given response 200`() {
        mockWebServer.enqueueResponse(mealsFilteredByAreaSourceFile, 200)
        runBlocking {
            val actual = dataSource.getMealsByCategory(mealArea).getOrThrow()
            val expected = mealsFilteredByArea

            assertThat(expected).isNotNull()
            assertThat(actual[0].mealId).isEqualTo(expected[0].mealId)
        }
    }

    @Test
    fun `should fetch meals filtered by category given response 200`() {
        mockWebServer.enqueueResponse(mealsFilteredByCategorySourceFile, 200)
        runBlocking {
            val actual = dataSource.getMealsByCategory(mealCategory).getOrThrow()
            val expected = mealsFilteredByCategory

            assertThat(expected).isNotNull()
            assertThat(actual[0].mealId).isEqualTo(expected[0].mealId)
        }
    }
}
