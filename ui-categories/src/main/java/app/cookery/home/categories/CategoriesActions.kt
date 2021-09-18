package app.cookery.home.categories

internal sealed class CategoriesAction {
    object RefreshAction : CategoriesAction()
    data class OpenMealDetails(val mealId: String) : CategoriesAction()
}
