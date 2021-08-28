package com.skorudzhiev.cookery.data.daos.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cookery.CookeryRoomDatabase
import app.cookery.data.daos.categories.CategoriesDao
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.data.allMealCategories
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
class CategoriesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CookeryRoomDatabase

    @Inject
    lateinit var dao: CategoriesDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_all_meal_categories() = runBlocking {
        dao.insert(allMealCategories)
        val observedCategories = dao.getAllMealCategories().take(1).firstOrNull()
        assertThat(observedCategories).isEqualTo(allMealCategories)
    }

    @Test
    fun get_all_meal_categories() = runBlocking {
        dao.insert(allMealCategories)
        val observedCategories = dao.getAllMealCategories().take(1).firstOrNull()
        assertThat(observedCategories).isNotNull()
        assertThat(observedCategories?.categories?.get(2)).isEqualTo(allMealCategories.categories[2])
    }
}
