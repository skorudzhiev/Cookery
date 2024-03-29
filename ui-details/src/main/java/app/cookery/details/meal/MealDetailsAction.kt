package app.cookery.details.meal

sealed class MealDetailsAction {
    object ClearError : MealDetailsAction()
    object UpdateFavoriteMeal : MealDetailsAction()
}
