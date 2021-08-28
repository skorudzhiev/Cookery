package com.skorudzhiev.cookery.data.daos.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cookery.CookeryRoomDatabase
import app.cookery.data.daos.categories.AreasDao
import com.google.common.truth.Truth.assertThat
import com.skorudzhiev.cookery.data.areas
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
class AreasDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CookeryRoomDatabase

    @Inject
    lateinit var dao: AreasDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_meal_areas() = runBlocking {
        dao.insert(areas)
        val observedAreas = dao.getMealAreas().take(1).firstOrNull()
        assertThat(observedAreas).isEqualTo(areas)
    }

    @Test
    fun get_meal_areas() = runBlocking {
        dao.insert(areas)
        val observedAreas = dao.getMealAreas().take(1).firstOrNull()
        assertThat(observedAreas).isNotNull()
        assertThat(observedAreas?.areas?.get(2)).isEqualTo(areas.areas[2])
    }
}
