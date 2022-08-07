package app.cookery.home.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.domain.observers.ObserveFavoriteMeals
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    observeFavoriteMeals: ObserveFavoriteMeals,
    private val snackbarManager: SnackbarManager
) : ViewModel() {

    private val loadingState = ObservableLoadingCounter()

    val state: StateFlow<FavoritesViewState> = combine(
        observeFavoriteMeals.flow,
        loadingState.observable
    ) { favorites, refreshing ->
        FavoritesViewState(
            favorites = favorites,
            refreshing = refreshing,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoritesViewState.Empty
    )

    init {
        observeFavoriteMeals(ObserveFavoriteMeals.Params())
    }

    internal fun clearError() {
        viewModelScope.launch {
            snackbarManager.removeCurrentError()
        }
    }
}
