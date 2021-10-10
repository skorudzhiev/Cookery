package com.skorudzhiev.cookery.data.daos.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cookery.CookeryRoomDatabase
import app.cookery.data.daos.categories.CategoryDetailsDao
import com.google.common.truth.Truth
import com.skorudzhiev.cookery.data.mealsFilteredByArea
import com.skorudzhiev.cookery.data.mealsFilteredByCategory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class CategoryDetailsDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CookeryRoomDatabase

    @Inject
    lateinit var dao: CategoryDetailsDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_meals_filtered_by_category() = runBlocking {
        dao.insertCategoryDetails(mealsFilteredByCategory, "Seafood", "")
        val observedMeals = dao.getCategoryDetailsByName("Seafood").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isEqualTo(mealsFilteredByCategory[0])
    }

    @Test
    fun get_meals_filtered_by_category() = runBlocking {
        dao.insertCategoryDetails(mealsFilteredByCategory, "Seafood", "")
        val observedMeals = dao.getCategoryDetailsByName("Seafood").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isNotNull()
        Truth.assertThat(observedMeals?.categoryName).isEqualTo(mealsFilteredByCategory[0].categoryName)
        Truth.assertThat(observedMeals?.mealId).isEqualTo(mealsFilteredByCategory[0].mealId)
    }

    @Test
    fun insert_meals_filtered_by_area() = runBlocking {
        dao.insertCategoryDetails(mealsFilteredByArea, "", "Canadian")
        val observedMeals = dao.getCategoryDetailsByArea("Canadian").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isEqualTo(mealsFilteredByArea[0])
    }

    @Test
    fun get_meals_filtered_by_area() = runBlocking {
        dao.insertCategoryDetails(mealsFilteredByArea, "", "Canadian")
        val observedMeals = dao.getCategoryDetailsByArea("Canadian").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isNotNull()
        Truth.assertThat(observedMeals?.categoryName).isEqualTo(mealsFilteredByArea[0].categoryName)
        Truth.assertThat(observedMeals?.mealId).isEqualTo(mealsFilteredByArea[0].mealId)
    }
}
