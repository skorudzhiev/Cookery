package app.cookery.details.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.domain.interactors.categories.UpdateMealsByCategory
import app.cookery.domain.observers.categories.ObserveCategoryWithCategoryDetails
import app.cookery.extensions.combine
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import com.cookery.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val observeCategoryWithCategoryDetails: ObserveCategoryWithCategoryDetails,
    private val updateMealsByCategory: UpdateMealsByCategory,
    private val snackbarManager: SnackbarManager,
    private val logger: Logger
) : ViewModel() {

    private val category: String = savedStateHandle.get("categoryName")!!
    private val categoriesLoadingState = ObservableLoadingCounter()
    private val pendingActions = MutableSharedFlow<CategoryDetailsAction>()

    val state: StateFlow<CategoryDetailsViewState> = combine(
        observeCategoryWithCategoryDetails.flow,
        categoriesLoadingState.observable
    ) { categoryDetails, categoriesLoad ->
        CategoryDetailsViewState(
            categoryWithCategoryDetails = categoryDetails,
            categoriesLoad
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoryDetailsViewState.Empty,
    )

    init {
        observeCategoryWithCategoryDetails(ObserveCategoryWithCategoryDetails.Params(category))

        viewModelScope.launch {
            updateCategoryDetails(category)
        }

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    CategoryDetailsAction.ClearError -> snackbarManager.removeCurrentError()
                }
            }
        }
    }

    fun submitAction(action: CategoryDetailsAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private suspend fun updateCategoryDetails(categoryName: String) {
        observeCategoryWithCategoryDetails.flow.collectLatest {
            if (it.isEmpty()) {
                updateMealsByCategory(UpdateMealsByCategory.Params(categoryName))
                    .watchStatus(
                        loadingCounter = categoriesLoadingState,
                        logger = logger,
                        snackbarManager = snackbarManager
                    )
            }
        }
    }
}
