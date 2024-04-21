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
import app.cookery.home.search.SearchActions.ClearError
import app.cookery.home.search.SearchActions.ClearSearchQuery
import app.cookery.home.search.SearchActions.OpenMealDetails
import app.cookery.home.search.SearchActions.SearchMealByName
import app.cookery.home.search.SearchScreenContentType.Recent
import app.cookery.home.search.SearchScreenContentType.SearchResults
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
) : BaseViewModel<SearchScreenState, SearchActions, SearchEffects>() {

    init {
        initializeObservers()
    }

    override fun createInitialScreenState() = SearchScreenState(
        recentSearches = null,
        lastOpenedMeals = null,
        searchResults = null,
        contentType = null,
        isSearching = false,
        error = null
    )

    override suspend fun handleActions(action: SearchActions) {
        when (action) {
            is ClearError -> snackbarManager.removeCurrentError()
            is SearchMealByName -> searchMealByName(action.mealName)
            is ClearSearchQuery -> updateScreenContentType(Recent)
            is CleanRecentSearchResults -> searchUseCases.cleanRecentSearchResultsUseCase()
            is OpenMealDetails -> openMealDetailsFromSearchResults(action.mealId)
        }
    }

    private fun updateScreenContentType(content: SearchScreenContentType) =
        viewModelScope.launch(coroutineDispatchers.io) {
            updateScreenState { copy(contentType = content) }
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

    private suspend fun openMealDetailsFromSearchResults(mealId: String) {
        searchUseCases.saveLastOpenedMealFromSearchUseCase(mealId)
        sendEffect(SearchEffects.OpenMealDetails(mealId))
    }

    private suspend fun searchMealByName(mealName: String) {
        updateSearchState(
            isSearching = true,
            searchResults = null
        )
        searchUseCases.searchMealByNameUseCase(mealName)
            .onSuccess { searchResults ->
                updateSearchState(
                    isSearching = false,
                    searchResults = searchResults
                )
            }
            .onFailure { throwable ->
                handleFailure(throwable)
            }
    }

    private fun updateSearchState(
        isSearching: Boolean,
        searchResults: List<MealDetails>?,
        contentType: SearchScreenContentType = SearchResults
    ) = viewModelScope.launch(coroutineDispatchers.io) {
        updateScreenState {
            copy(
                isSearching = isSearching,
                searchResults = searchResults,
                contentType = contentType
            )
        }
    }

    private fun handleFailure(throwable: Throwable) {
        viewModelScope.launch(coroutineDispatchers.io) {
            snackbarManager.addError(getUiError(throwable))
            updateScreenState { copy(isSearching = false) }
            logger.e(throwable)
        }
    }
}
