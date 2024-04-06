package app.cookery.home.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.db.entities.MealDetails
import app.cookery.domain.interactors.UpdateMealDetails
import app.cookery.domain.interactors.UpdateRandomMeal
import app.cookery.domain.observers.random.ObserveLastMeal
import app.cookery.extensions.combine
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.ClearError
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.UpdateFavoriteMeal
import com.cookery.api.UiError
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
    private val updateMealDetails: UpdateMealDetails,
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

    internal fun getRandomMealFromRemote() {
        updateRandomMeal(Unit).watchStatus(
            loadingCounter = loadingState,
            viewModelScope = viewModelScope,
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
        getRandomMealFromRemote()
        viewModelScope.launch {
            updateMealDetails.updateFavoriteMeal(
                params = UpdateMealDetails.Params(mealId = getRandomMealId()),
                isMarkedAsFavorite = state.value.isMealMarkedAsFavorite
            )
        }
    }

    private fun getRandomMealId() = state.value.randomMeal!!.mealId

    companion object {

        sealed class RandomMealAction {
            object ClearError : RandomMealAction()
            object UpdateFavoriteMeal : RandomMealAction()
        }

        internal data class RandomMealViewState(
            var randomMeal: MealDetails? = null,
            val refreshing: Boolean = false,
            val isMealMarkedAsFavorite: Boolean = false,
            val error: UiError? = null
        ) {
            companion object {
                val Empty = RandomMealViewState()
            }
        }
    }
}
