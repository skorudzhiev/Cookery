package com.skorudzhiev.cookery.data.daos.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cookery.CookeryRoomDatabase
import app.cookery.data.daos.categories.FilterByCategoryDao
import com.google.common.truth.Truth
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
class FilterByCategoriesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CookeryRoomDatabase

    @Inject
    lateinit var dao: FilterByCategoryDao

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
        dao.insert(mealsFilteredByCategory)
        val observedMeals = dao.getMealsFilteredByCategory("Seafood").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isEqualTo(mealsFilteredByCategory)
    }

    @Test
    fun get_meals_filtered_by_category() = runBlocking {
        dao.insert(mealsFilteredByCategory)
        val observedMeals = dao.getMealsFilteredByCategory("Seafood").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isNotNull()
        Truth.assertThat(observedMeals?.category).isEqualTo(mealsFilteredByCategory.category)
        Truth.assertThat(observedMeals?.meals?.get(0)).isEqualTo(mealsFilteredByCategory.meals[0])
    }
}
