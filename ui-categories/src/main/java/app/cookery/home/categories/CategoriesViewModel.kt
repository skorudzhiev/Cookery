package app.cookery.home.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cookery.DataStore
import app.cookery.Logger
import app.cookery.data.InvokeError
import app.cookery.data.InvokeStarted
import app.cookery.data.InvokeSuccess
import app.cookery.domain.interactors.InitializeHomeScreenData
import app.cookery.domain.interactors.UpdateAllMealCategories
import app.cookery.domain.interactors.UpdateAreas
import app.cookery.domain.observers.ObserveAreas
import app.cookery.domain.observers.ObserveCategories
import app.cookery.domain.observers.ObserveRandomAreaMeals
import app.cookery.domain.observers.ObserveRandomCategoryMeals
import app.cookery.extensions.combine
import com.cookery.api.UiError
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
internal class CategoriesViewModel @Inject constructor(
    private val initializeHomeScreenData: InitializeHomeScreenData,
    private val updateAllMealCategories: UpdateAllMealCategories,
    private val updateAreas: UpdateAreas,
    private val snackbarManager: SnackbarManager,
    private val dataStore: DataStore,
    private val logger: Logger,
    observeCategories: ObserveCategories,
    observeAreas: ObserveAreas,
    observeRandomCategoryMeals: ObserveRandomCategoryMeals,
    observeRandomAreaMeals: ObserveRandomAreaMeals
) : ViewModel() {

    private val categoriesLoadingState = ObservableLoadingCounter()
    private val areasLoadingState = ObservableLoadingCounter()
    private val randomCategoriesLoadingState = ObservableLoadingCounter()
    private val randomAreasLoadingState = ObservableLoadingCounter()

    private val pendingActions = MutableSharedFlow<CategoriesAction>()

    val state: StateFlow<CategoriesViewState> = combine(
        categoriesLoadingState.observable,
        areasLoadingState.observable,
        randomCategoriesLoadingState.observable,
        randomAreasLoadingState.observable,
        snackbarManager.errors,
        observeCategories.flow,
        observeAreas.flow,
        observeRandomCategoryMeals.flow,
        observeRandomAreaMeals.flow
    ) { categoriesLoad, areasLoad, randomCategoriesLoad, randomAreasLoad, errors, categories, areas, randomCategoriesMeals, randomAreaMeals ->
        CategoriesViewState(
            mealCategories = categories,
            categoriesRefreshing = categoriesLoad,
            areaMeals = areas,
            areasRefreshing = areasLoad,
            popularMeals = randomCategoriesMeals,
            randomCategoriesRefreshing = randomCategoriesLoad,
            recommendedMeals = randomAreaMeals,
            randomAreasRefreshing = randomAreasLoad,
            error = errors
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoriesViewState.Empty,
    )

    init {
        viewModelScope.launch(viewModelScope.coroutineContext) {
            dataStore.isAppDataInitialized().collect { isInitialized ->
                if (!isInitialized) {
                    initializeData()
                }
            }
        }

        observeCategories(ObserveCategories.Params())
        observeAreas(ObserveAreas.Params())
        observeRandomCategoryMeals(ObserveRandomCategoryMeals.Params())
        observeRandomAreaMeals(ObserveRandomAreaMeals.Params())

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    CategoriesAction.RefreshAction -> refresh()
                    CategoriesAction.ClearError -> snackbarManager.removeCurrentError()
                    else -> {}
                }
            }
        }
    }

    fun submitAction(action: CategoriesAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun initializeData() {
        viewModelScope.launch {
            initializeHomeScreenData(InitializeHomeScreenData.Params()).collect { status ->
                when (status) {
                    InvokeStarted -> categoriesLoadingState.addLoader()
                    InvokeSuccess -> {
                        categoriesLoadingState.removeLoader()
                        dataStore.setAppInitializationState(true)
                    }
                    is InvokeError -> {
                        logger.i(status.throwable)
                        snackbarManager.addError(UiError(status.throwable))
                        categoriesLoadingState.removeLoader()
                    }
                }
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            updateAllMealCategories(UpdateAllMealCategories.Params())
                .watchStatus(
                    loadingCounter = categoriesLoadingState,
                    viewModelScope = viewModelScope,
                    logger = logger,
                    snackbarManager = snackbarManager
                )
        }
        viewModelScope.launch {
            updateAreas(UpdateAreas.Params())
                .watchStatus(
                    loadingCounter = categoriesLoadingState,
                    viewModelScope = viewModelScope,
                    logger = logger,
                    snackbarManager = snackbarManager
                )
        }
    }
}
