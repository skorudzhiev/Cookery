package app.cookery.home.random

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.data.entities.MealDetails
import app.cookery.domain.interactors.UpdateRandomMeal
import app.cookery.domain.observers.random.ObserveRandomMeal
import app.cookery.extensions.combine
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import com.cookery.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RandomMealViewModel @Inject constructor(
    private val observeRandomMeal: ObserveRandomMeal,
    private val updateRandomMeal: UpdateRandomMeal,
    private val snackbarManager: SnackbarManager,
    private val logger: Logger
) : ViewModel() {

    private val loadingState = ObservableLoadingCounter()

    val state: StateFlow<RandomMealViewState> = combine(
        observeRandomMeal.flow,
        loadingState.observable
    ) { randomMeal, refreshing ->
        RandomMealViewState(
            randomMeal = randomMeal,
            refreshing = refreshing,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RandomMealViewState.Empty
    )

    init {
        observeRandomMeal(Unit)

        viewModelScope.launch {
            retrieveRandomMeal()
        }
    }

    private suspend fun retrieveRandomMeal() {
        observeRandomMeal.flow.collectLatest {
            if (it == null) {
                updateRandomMeal(Unit).watchStatus(
                    loadingCounter = loadingState,
                    viewModelScope = viewModelScope,
                    logger = logger,
                    snackbarManager = snackbarManager
                )
            }
        }
    }

    companion object {

        @Immutable
        internal data class RandomMealViewState(
            val randomMeal: MealDetails? = null,
            val refreshing: Boolean = false,
        ) {
            companion object {
                val Empty = RandomMealViewState()
            }
        }
    }
}
