package app.cookery.home.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.domain.interactors.UpdateAllMealCategories
import app.cookery.domain.interactors.UpdateAreas
import app.cookery.domain.observers.ObserveAllMealCategories
import app.cookery.domain.observers.ObserveAreas
import com.cookery.util.ObservableLoadingCounter
import com.cookery.util.collectInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CategoriesViewModel @Inject constructor(
    private val updateAllMealCategories: UpdateAllMealCategories,
    private val updateAreas: UpdateAreas,
    observeAllMealCategories: ObserveAllMealCategories,
    observeAreas: ObserveAreas
) : ViewModel() {

    private val allCategoriesLoadingState = ObservableLoadingCounter()
    private val allAreasLoadingState = ObservableLoadingCounter()

    private val pendingActions = MutableSharedFlow<CategoriesAction>()

    val state: StateFlow<CategoriesViewState> = combine(
        allCategoriesLoadingState.observable,
        allAreasLoadingState.observable,
        observeAllMealCategories.flow,
        observeAreas.flow,
    ) { categoriesLoad, areasLoad, categories, areas ->
        CategoriesViewState(
            allMealCategories = categories,
            categoriesRefreshing = categoriesLoad,
            allMealAreas = areas,
            areasRefreshing = areasLoad,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoriesViewState.Empty,
    )

    init {
        observeAllMealCategories(ObserveAllMealCategories.Params())
        observeAreas(ObserveAreas.Params())

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    CategoriesAction.RefreshAction -> refresh()
                }
            }
        }
    }

    fun submitAction(action: CategoriesAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            updateAllMealCategories(UpdateAllMealCategories.Params())
                .collectInfo(allCategoriesLoadingState)
        }
        viewModelScope.launch {
            updateAreas(UpdateAreas.Params())
                .collectInfo(allAreasLoadingState)
        }
    }
}
