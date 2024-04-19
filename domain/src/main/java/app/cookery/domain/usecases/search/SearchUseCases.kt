package app.cookery.domain.usecases.search

import app.cookery.domain.observers.search.ObserveLastOpenedMeals
import app.cookery.domain.observers.search.ObserveRecentSearches
import javax.inject.Inject

class SearchUseCases @Inject constructor(
    val observeRecentSearches: ObserveRecentSearches,
    val observeLastOpenedMeals: ObserveLastOpenedMeals,
    val searchMealByNameUseCase: SearchMealByNameUseCase,
    val cleanRecentSearchResultsUseCase: CleanRecentSearchResultsUseCase,
    val saveLastOpenedMealFromSearchUseCase: SaveLastOpenedMealFromSearchUseCase
)
