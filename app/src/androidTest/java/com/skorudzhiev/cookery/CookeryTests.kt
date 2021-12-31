package com.skorudzhiev.cookery

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.skorudzhiev.cookery.ui.CookeryApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CookeryTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CookeryApp()
        }
    }

    @Test
    fun app_launches_and_correct_items_are_displayed_on_home() {
        composeTestRule.onNodeWithText("HOME").assertIsDisplayed()

        composeTestRule.onNodeWithTag("Popular meals list").assertExists("")
        composeTestRule.onNodeWithTag("All meals categories list").assertExists("")
        composeTestRule.onNodeWithTag("Recommended meals list").assertExists("")
    }

    @Test
    fun app_navigates_through_the_bottom_app_bar_screens() {
        composeTestRule.onNodeWithText("SEARCH").performClick().assertIsDisplayed()
        composeTestRule.onNodeWithText("Search").assertIsDisplayed()

        composeTestRule.onNodeWithText("FAVORITES").performClick().assertIsDisplayed()
        composeTestRule.onNodeWithText("Favorites").assertIsDisplayed()

        composeTestRule.onNodeWithText("RANDOM").performClick().assertIsDisplayed()
        composeTestRule.onNodeWithText("Random").assertIsDisplayed()
    }

    @Test
    fun app_navigates_to_meal_details_screen() {
        composeTestRule.onNodeWithTag("Popular meals list").performClick()
        composeTestRule.onNodeWithTag("Meal details").assertIsDisplayed()
        composeTestRule.onNodeWithText("Category -").assertIsDisplayed()
        composeTestRule.onNodeWithText("Origin -").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Favorites button").assertIsDisplayed()
    }

    @Test
    fun app_navigates_to_category_details_screen() {
        composeTestRule.onNodeWithTag("All meals categories list").performClick()
        composeTestRule.onNodeWithTag("Category details").assertExists("")
    }
}
