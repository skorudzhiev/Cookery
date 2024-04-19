package app.cookery.home.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.domain.interactors.UpdateRandomMeal
import app.cookery.domain.model.MealDetails
import app.cookery.domain.observers.random.ObserveLastMeal
import app.cookery.domain.usecases.UpdateFavoriteMealUseCase
import app.cookery.extensions.combine
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.ClearError
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.UpdateFavoriteMeal
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
internal class RandomMealViewModel @Inject constructor(
    private val observeLastMeal: ObserveLastMeal,
    private val updateRandomMeal: UpdateRandomMeal,
    private val updateFavoriteMeal: UpdateFavoriteMealUseCase,
    private val snackBarManager: SnackbarManager,
    private val logger: Logger
) : ViewModel() {

    private val loadingState = ObservableLoadingCounter()
    private val pendingActions = MutableSharedFlow<RandomMealAction>()

    internal val state: StateFlow<RandomMealViewState> by lazy {
        combine(
            observeLastMeal.flow,
            loadingState.observable,
        ) { lastMeal, refreshing ->
            RandomMealViewState(
                randomMeal = lastMeal,
                refreshing = refreshing,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RandomMealViewState.Empty
        )
    }

    init {
        collectPendingActions()
        observeLastMeal(Unit)
    }

    internal fun submitAction(action: RandomMealAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    internal suspend fun getRandomMealFromRemote() {
        updateRandomMeal(Unit).watchStatus(
            loadingCounter = loadingState,
            logger = logger,
            snackbarManager = snackBarManager
        )
    }

    private fun collectPendingActions() {
        viewModelScope.launch {
            pendingActions.collectLatest { action ->
                when (action) {
                    ClearError -> snackBarManager.removeCurrentError()
                    UpdateFavoriteMeal -> updateFavoriteMeal()
                }
            }
        }
    }

    private fun updateFavoriteMeal() {
        viewModelScope.launch {
            getRandomMealFromRemote()
            updateFavoriteMeal.invoke(
                mealId = getRandomMealId().toString(),
                isMarkedAsFavorite = state.value.isMealMarkedAsFavorite
            )
        }
    }

    private fun getRandomMealId() = state.value.randomMeal?.mealId

    companion object {

        sealed class RandomMealAction {
            data object ClearError : RandomMealAction()
            data object UpdateFavoriteMeal : RandomMealAction()
        }

        internal data class RandomMealViewState(
            var randomMeal: MealDetails? = null,
            val refreshing: Boolean = false,
            val isMealMarkedAsFavorite: Boolean = false,
            val error: String? = null
        ) {
            companion object {
                val Empty = RandomMealViewState()
            }
        }
    }
}
