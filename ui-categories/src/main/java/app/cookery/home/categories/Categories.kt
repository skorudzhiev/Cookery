package app.cookery.home.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.data.entities.categories.MealCollection
import app.cookery.data.entities.categories.MealsCollection
import app.cookery.data.entities.categories.MockedData
import com.cookery.common.compose.components.CategoryTypes
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.google.accompanist.insets.statusBarsHeight
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toCollection

@Composable
fun Categories(
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: Replace with stored data
    val viewModel: CategoriesViewModel = hiltViewModel()
    val viewState by viewModel.state.collectAsState()

    val mealsCollection = remember { MockedData.getMealsCollection() }
    Categories(
        mealCollection = mealsCollection,
        onMealClicked = onItemClicked
    )
}

@Composable
private fun Categories(
    mealCollection: List<MealCollection>,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            CategoriesCollectionList(
                mealCollection = mealCollection,
                onItemClicked = onMealClicked
            )
        }
    }
}

@Composable
private fun CategoriesCollectionList(
    mealCollection: List<MealCollection>,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        LazyColumn {
            item {
                Spacer(Modifier.statusBarsHeight(additional = 24.dp))
            }
            itemsIndexed(mealCollection) { index, collection ->
                CategoryTypes(
                    mealCollection = collection,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}
