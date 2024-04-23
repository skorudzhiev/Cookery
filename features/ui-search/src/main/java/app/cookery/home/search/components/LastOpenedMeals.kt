package app.cookery.home.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cookery.domain.model.CategoryDetails
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.RandomizedMeals
import com.cookery.common.compose.modifiers.Layout
import com.skorudzhiev.cookery.ui.search.R

@Composable
fun LastOpenedMeals(
    lastOpenedMeals: List<CategoryDetails>,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val canExpand = lastOpenedMeals.size > 5
    val isExpanded = remember { mutableStateOf(false) }

    val buttonText = if (isExpanded.value) {
        R.string.label_see_less
    } else {
        R.string.label_see_all
    }

    HeaderAndTextButtonRow(
        headerText = R.string.label_last_opened_meals,
        buttonText = buttonText,
        shouldShowButton = canExpand,
        modifier = modifier.fillMaxWidth(),
        onClick = { isExpanded.value = !isExpanded.value }
    )
    Spacer(modifier = Modifier.padding(top = 8.dp))

    if (isExpanded.value) {
        LazyColumn {
            items(lastOpenedMeals) { meal ->
                LastOpenedMealsList(
                    categoryDetails = meal,
                    openMealDetails = onMealClicked
                )
            }
        }
    } else {
        RandomizedMeals(
            categoryMeals = lastOpenedMeals.take(5),
            openMealDetails = onMealClicked
        )
    }
}

@Composable
private fun LastOpenedMealsList(
    categoryDetails: CategoryDetails,
    openMealDetails: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { openMealDetails(categoryDetails.mealId) })
            .fillMaxSize()
            .padding(
                horizontal = Layout.bodyMargin,
                vertical = 2.dp
            )
    ) {
        CategoryDetailsItem(
            imageUrl = categoryDetails.mealImage,
            mealDescription = categoryDetails.mealName
        )
    }
}
