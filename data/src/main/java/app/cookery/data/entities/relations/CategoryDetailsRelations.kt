package app.cookery.data.entities.relations

data class CategoryWithCategoryDetails(
    val mealId: String,
    val mealName: String,
    val mealImage: String,
    val categoryName: String,
    val categoryImage: String,
    val categoryDescription: String? = null
)

data class AreaWithCategoryDetails(
    val mealId: String,
    val mealName: String,
    val mealImage: String,
    val area: String
)
