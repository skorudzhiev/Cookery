package app.cookery.home.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cookery.data.entities.categories.AllMealCategories
import com.cookery.common.compose.components.CategoryTypes
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun Categories(
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: Replace with stored data
    val allMealCategories = remember { MockedData.getCategories() }
    Categories(
        mealCategories = allMealCategories,
        onMealClicked = onMealClicked
    )
}

@Composable
private fun Categories(
    mealCategories: AllMealCategories,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            CategoriesCollectionList(
                allMealCategories = mealCategories,
                onMealClicked = onMealClicked
            )
        }
    }
}

@Composable
private fun CategoriesCollectionList(
    allMealCategories: AllMealCategories,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        LazyColumn {
            // TODO: CategoriesCollection in itemsIndexed
            item {
                Spacer(Modifier.statusBarsHeight(additional = 24.dp))
                CategoryTypes(
                    mealCategories = allMealCategories,
                    onMealClicked = onMealClicked
                )
            }
        }
    }
}
