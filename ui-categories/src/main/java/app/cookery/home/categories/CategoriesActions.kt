package app.cookery.home.categories

internal sealed class CategoriesAction {
    object RefreshAction : CategoriesAction()
    object InitializeData : CategoriesAction()
    data class OpenMealDetails(val mealId: String) : CategoriesAction()
    data class OpenCategoryDetailsByName(val categoryName: String) : CategoriesAction()
    data class OpenCategoryDetailsByArea(val area: String) : CategoriesAction()
}
