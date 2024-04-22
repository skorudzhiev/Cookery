package app.cookery.home.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cookery.domain.model.MealDetails
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.DisplayPlaceholder
import com.cookery.common.compose.modifiers.Layout
import com.skorudzhiev.cookery.ui.search.R

@Composable
fun SearchResults(
    searchResults: List<MealDetails>,
    onMealClicked: (String) -> Unit
) {
    if (searchResults.isEmpty()) {
        DisplayPlaceholder(
            image = R.drawable.recipe_not_found,
            text = R.string.search_no_results,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        LazyColumn {
            items(searchResults) { meal ->
                MealItem(
                    meal = meal,
                    openMealDetails = onMealClicked
                )
            }
        }
    }
}

@Composable
private fun MealItem(
    meal: MealDetails,
    openMealDetails: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { openMealDetails(meal.mealId.toString()) })
            .fillMaxSize()
            .padding(
                horizontal = Layout.bodyMargin,
                vertical = 2.dp
            )
    ) {
        CategoryDetailsItem(
            imageUrl = meal.mealImage,
            mealDescription = meal.mealName
        )
    }
}
