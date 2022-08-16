package app.cookery.details.area

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.Logger
import app.cookery.domain.interactors.areas.UpdateMealsByArea
import app.cookery.domain.observers.areas.ObserveAreaWithCategoryDetails
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import com.cookery.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaDetailsViewModel @Inject constructor(
    private val observeAreaDetails: ObserveAreaWithCategoryDetails,
    private val updateMealsByArea: UpdateMealsByArea,
    savedStateHandle: SavedStateHandle,
    private val snackbarManager: SnackbarManager,
    private val logger: Logger
) : ViewModel() {

    private val area: String = savedStateHandle.get("areaName")!!
    private val loadingState = ObservableLoadingCounter()

    val state: StateFlow<AreaDetailsViewState> = combine(
        observeAreaDetails.flow,
        loadingState.observable
    ) { areaDetails, loading ->
        AreaDetailsViewState(
            areaWithCategoryDetails = areaDetails,
            refreshing = loading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AreaDetailsViewState.Empty
    )

    init {
        observeAreaDetails(ObserveAreaWithCategoryDetails.Params(area))
        updateAreaDetails()
    }

    fun clearError() {
        viewModelScope.launch {
            snackbarManager.removeCurrentError()
        }
    }

    private fun updateAreaDetails() {
        viewModelScope.launch {
            observeAreaDetails.flow.collect {
                if (it.isEmpty()) {
                    updateMealsByArea(UpdateMealsByArea.Params(area))
                        .watchStatus(
                            loadingCounter = loadingState,
                            viewModelScope = viewModelScope,
                            logger = logger,
                            snackbarManager = snackbarManager
                        )
                }
            }
        }
    }
}
