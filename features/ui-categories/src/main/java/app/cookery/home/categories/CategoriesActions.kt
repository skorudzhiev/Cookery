package app.cookery.home.categories

internal sealed class CategoriesActions {
    object RefreshActions : CategoriesActions()
    object ClearError : CategoriesActions()
}
