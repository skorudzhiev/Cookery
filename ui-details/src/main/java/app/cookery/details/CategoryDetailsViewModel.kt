package app.cookery.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.domain.observers.ObserveCategoryWithCategoryDetails
import app.cookery.extensions.combine
import com.cookery.util.ObservableLoadingCounter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    observeCategoryWithCategoryDetails: ObserveCategoryWithCategoryDetails
) : ViewModel() {

    private val categoriesLoadingState = ObservableLoadingCounter()

    val state: StateFlow<CategoryDetailsViewState> = combine(
        observeCategoryWithCategoryDetails.flow,
        categoriesLoadingState.observable
    ) { categoryDetails, _ ->
        CategoryDetailsViewState(
            categoryWithCategoryDetails = categoryDetails
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoryDetailsViewState.Empty,
    )
}
