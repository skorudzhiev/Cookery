package app.cookery.domain.model

data class Category(
    val categoryName: String,
    val categoryId: String,
    val categoryImage: String?,
    val categoryDescription: String?
)
