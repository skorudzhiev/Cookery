package com.skorudzhiev.cookery.data.daos.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cookery.CookeryRoomDatabase
import app.cookery.data.daos.categories.FilterByAreaDao
import com.google.common.truth.Truth
import com.skorudzhiev.cookery.data.mealsFilteredByArea
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
class FilterByAreaDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CookeryRoomDatabase

    @Inject
    lateinit var dao: FilterByAreaDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_meals_filtered_by_area() = runBlocking {
        dao.insert(mealsFilteredByArea)
        val observedMeals = dao.getMealsFilteredByArea("Canadian").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isEqualTo(mealsFilteredByArea)
    }

    @Test
    fun get_meals_filtered_by_area() = runBlocking {
        dao.insert(mealsFilteredByArea)
        val observedMeals = dao.getMealsFilteredByArea("Canadian").take(1).firstOrNull()
        Truth.assertThat(observedMeals).isNotNull()
        Truth.assertThat(observedMeals?.area).isEqualTo(mealsFilteredByArea.area)
        Truth.assertThat(observedMeals?.meals?.get(0)).isEqualTo(mealsFilteredByArea.meals[0])
    }
}
