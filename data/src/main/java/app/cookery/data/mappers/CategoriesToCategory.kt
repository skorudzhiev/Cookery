package app.cookery.data.mappers

import app.cookery.data.entities.categories.Category
import app.cookery.data.models.Categories
import app.cookery.data.models.MealCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesToCategory @Inject constructor(
    private val mapper: MealCategoryToCategory
) : Mapper<Categories, List<Category>> {
    override suspend fun map(from: Categories): List<Category> = from.categories.map { mapper.map(it) }
}

@Singleton
class MealCategoryToCategory @Inject constructor() : Mapper<MealCategory, Category> {
    override suspend fun map(from: MealCategory) = Category(
        categoryName = from.categoryName,
        categoryId = from.categoryId,
        categoryImage = from.categoryImage,
        categoryDescription = from.categoryDescription
    )
}
