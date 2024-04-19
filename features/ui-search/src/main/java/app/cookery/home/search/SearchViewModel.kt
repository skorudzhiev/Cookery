package app.cookery.home.search

import androidx.lifecycle.viewModelScope
import app.cookery.AppCoroutineDispatchers
import app.cookery.ErrorMapper
import app.cookery.Logger
import app.cookery.data.onFailure
import app.cookery.data.onSuccess
import app.cookery.domain.model.MealDetails
import app.cookery.domain.usecases.search.SearchUseCases
import app.cookery.home.search.SearchActions.CleanRecentSearchResults
import app.cookery.home.search.SearchActions.OpenMealDetails
import app.cookery.home.search.SearchActions.SearchMealByName
import app.cookery.presentation.getUiError
import com.cookery.BaseViewModel
import com.cookery.ui.SnackbarManager
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases,
    private val coroutineDispatchers: AppCoroutineDispatchers,
    private val snackbarManager: SnackbarManager,
    private val logger: Logger,
    private val errorMapper: ErrorMapper
) : BaseViewModel<SearchState, SearchActions, SearchEffects>() {

    init {
        initializeObservers()
    }

    override fun createInitialScreenState() = SearchState(
        recentSearches = emptyList(),
        lastOpenedMeals = emptyList(),
        searchResults = emptyList(),
        isLoading = false,
        error = null
    )

    override suspend fun handleActions(action: SearchActions) {
        when (action) {
            is CleanRecentSearchResults -> searchUseCases.cleanRecentSearchResultsUseCase()
            is OpenMealDetails -> openMealDetailsFromSearchResults(action.mealId)
            is SearchMealByName -> searchMealByName(action.mealName)
        }
    }

    private suspend fun openMealDetailsFromSearchResults(mealId: String) {
        searchUseCases.saveLastOpenedMealFromSearchUseCase(mealId)
        sendEffect(SearchEffects.OpenMealDetails(mealId))
    }

    private fun initializeObservers() {
        viewModelScope.launch(coroutineDispatchers.io) {
            combine(
                searchUseCases.observeLastOpenedMeals.flow,
                searchUseCases.observeRecentSearches.flow,
                snackbarManager.errors
            ) { lastOpenedMeals, recentSearches, errors ->
                updateScreenState {
                    copy(
                        recentSearches = recentSearches,
                        lastOpenedMeals = lastOpenedMeals,
                        error = errorMapper.mapError(errors?.errorType)
                    )
                }
            }
        }
    }

    private suspend fun searchMealByName(mealName: String) {
        updateScreenState { copy(isLoading = true) }
        searchUseCases.searchMealByNameUseCase(mealName)
            .onSuccess { searchResults ->
                handleSuccess(searchResults)
            }
            .onFailure { throwable ->
                handleFailure(throwable)
            }
    }

    private fun handleSuccess(searchResults: List<MealDetails>) {
        viewModelScope.launch(coroutineDispatchers.io) {
            updateScreenState {
                copy(
                    searchResults = searchResults,
                    isLoading = false
                )
            }
        }
    }

    private fun handleFailure(throwable: Throwable) {
        viewModelScope.launch(coroutineDispatchers.io) {
            snackbarManager.addError(getUiError(throwable))
            updateScreenState { copy(isLoading = false) }
            logger.e(throwable)
        }
    }
}
