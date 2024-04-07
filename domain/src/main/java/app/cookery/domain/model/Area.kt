package app.cookery.domain.model

data class Area(val area: String)

data class AreaWithCategoryDetails(
    val mealId: String,
    val mealName: String,
    val mealImage: String,
    val area: String
)
