package app.cookery.home.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.domain.interactors.UpdateAllMealCategories
import app.cookery.domain.interactors.UpdateAreas
import app.cookery.domain.observers.ObserveMealsCollection
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
    observeMealsCollection: ObserveMealsCollection
) : ViewModel() {

    private val mealsCollectionLoadingState = ObservableLoadingCounter()
    private val pendingActions = MutableSharedFlow<CategoriesAction>()

    val state: StateFlow<CategoriesViewState> = combine(
        mealsCollectionLoadingState.observable,
        observeMealsCollection.flow,
    ) { collectionsLoad, collections ->
        CategoriesViewState(
            mealsCollection = collections,
            collectionRefreshing = collectionsLoad,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoriesViewState.Empty,
    )

    init {
        observeMealsCollection(ObserveMealsCollection.Params())

        refresh()
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
                .collectInfo(mealsCollectionLoadingState)
        }
        viewModelScope.launch {
            updateAreas(UpdateAreas.Params())
                .collectInfo(mealsCollectionLoadingState)
        }
    }
}
