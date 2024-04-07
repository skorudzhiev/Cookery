package app.cookery.domain.model

data class CategoryDetails(
    val mealId: String,
    val mealName: String,
    val mealImage: String,
    val categoryName: String?,
    val area: String?
)

data class CategoryWithCategoryDetails(
    val mealId: String,
    val mealName: String,
    val mealImage: String,
    val categoryName: String,
    val categoryImage: String,
    val categoryDescription: String? = null
)
