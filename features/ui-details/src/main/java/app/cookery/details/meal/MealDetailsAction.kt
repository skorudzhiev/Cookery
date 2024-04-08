package app.cookery.details.meal

sealed class MealDetailsAction {
    data object ClearError : MealDetailsAction()
    data object UpdateFavoriteMeal : MealDetailsAction()
}
