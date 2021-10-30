package com.skorudzhiev.cookery.data.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cookery.CookeryRoomDatabase
import app.cookery.data.daos.MealDao
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.data.mealDetails
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MealDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CookeryRoomDatabase

    @Inject
    lateinit var dao: MealDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_meal() = runBlockingTest {
        dao.insertMealDetails(mealDetails)
        val observedMeals = dao.getMealDetails("52772").take(1).firstOrNull()
        assertThat(observedMeals).isEqualTo(mealDetails[0])
    }

    @Test
    fun get_meal_details() = runBlockingTest {
        dao.insertMealDetails(mealDetails)
        val observedMeals = dao.getMealDetails("52772").take(1).firstOrNull()
        assertThat(observedMeals).isNotNull()
        assertThat(observedMeals?.mealName).isEqualTo(mealDetails[0].mealName)
    }
}
