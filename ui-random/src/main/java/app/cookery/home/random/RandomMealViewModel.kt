package app.cookery.home.random

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.data.entities.MealDetails
import app.cookery.domain.interactors.UpdateMealDetails
import app.cookery.domain.interactors.UpdateRandomMeal
import app.cookery.domain.observers.ObserveMealDetails
import app.cookery.domain.observers.random.ObserveRandomMeal
import app.cookery.extensions.combine
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.ClearError
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.UpdateFavoriteMeal
import com.cookery.api.UiError
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import com.cookery.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RandomMealViewModel @Inject constructor(
    private val observeRandomMeal: ObserveRandomMeal,
    private val observeMealDetails: ObserveMealDetails,
    private val updateRandomMeal: UpdateRandomMeal,
    private val updateMealDetails: UpdateMealDetails,
    private val snackBarManager: SnackbarManager,
    private val logger: Logger
) : ViewModel() {

    private val loadingState = ObservableLoadingCounter()
    private val pendingActions = MutableSharedFlow<RandomMealAction>()

    private var randomMealId: String = ""

    internal val state: StateFlow<RandomMealViewState> = combine(
        observeRandomMeal.flow,
        loadingState.observable,
        observeMealFavoriteState()
    ) { randomMeal, refreshing, favoriteMeal ->
        RandomMealViewState(
            randomMeal = randomMeal,
            refreshing = refreshing,
            isMealMarkedAsFavorite = isFavorite(favoriteMeal),
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RandomMealViewState.Empty
    )

    init {
        collectPendingActions()
    }

    internal fun submitAction(action: RandomMealAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
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
            updateMealDetails.updateFavoriteMeal(
                params = UpdateMealDetails.Params(mealId = randomMealId),
                isMarkedAsFavorite = state.value.isMealMarkedAsFavorite
            )
        }
    }

    private fun observeMealFavoriteState(): Flow<String> = observeMealDetails.isMarkedAsFavorite(
        ObserveMealDetails.Params(mealId = randomMealId)
    )

    private fun isFavorite(storedMealId: String?): Boolean = let { storedMealId == randomMealId }

    internal suspend fun retrieveRandomMeal() {
        observeRandomMeal(Unit)
        observeRandomMeal.flow.collectLatest { mealDetails ->
            mealDetails?.let {
                randomMealId = it.mealId
            } ?: run {
                updateRandomMeal(Unit).watchStatus(
                    loadingCounter = loadingState,
                    viewModelScope = viewModelScope,
                    logger = logger,
                    snackbarManager = snackBarManager
                )
            }
        }
    }

    companion object {

        sealed class RandomMealAction {
            object ClearError : RandomMealAction()
            object UpdateFavoriteMeal : RandomMealAction()
        }

        @Immutable
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
