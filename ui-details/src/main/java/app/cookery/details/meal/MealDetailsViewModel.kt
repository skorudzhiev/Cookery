package app.cookery.details.meal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.domain.interactors.UpdateMealDetails
import app.cookery.domain.observers.ObserveMealDetails
import app.cookery.extensions.combine
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import com.cookery.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val observeMealDetails: ObserveMealDetails,
    private val updateMealDetails: UpdateMealDetails,
    private val snackbarManager: SnackbarManager,
    private val logger: Logger
) : ViewModel() {

    private val meal: String = savedStateHandle.get("mealId")!!
    private val mealLoadingState = ObservableLoadingCounter()
    private val pendingActions = MutableSharedFlow<MealDetailsAction>()

    val state: StateFlow<MealDetailsViewState> = combine(
        observeMealDetails.flow,
        mealLoadingState.observable
    ) { mealDetails, mealLoading ->
        MealDetailsViewState(
            mealDetails = mealDetails,
            refreshing = mealLoading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MealDetailsViewState.Empty,
    )

    init {
        observeMealDetails(ObserveMealDetails.Params(meal))

        viewModelScope.launch {
            retrieveMealDetails()

            pendingActions.collect { action ->
                when (action) {
                    MealDetailsAction.ClearError -> snackbarManager.removeCurrentError()
                }
            }
        }
    }

    fun submitAction(action: MealDetailsAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private suspend fun retrieveMealDetails() {
        observeMealDetails.flow.collect {
            if (it == null) {
                updateMealDetails(UpdateMealDetails.Params(meal))
                    .watchStatus(
                        loadingCounter = mealLoadingState,
                        viewModelScope = viewModelScope,
                        logger = logger,
                        snackbarManager = snackbarManager
                    )
            }
        }
    }
}
