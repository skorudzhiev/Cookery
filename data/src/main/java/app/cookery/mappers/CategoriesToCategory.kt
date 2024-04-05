package app.cookery.mappers

import app.cookery.db.entities.categories.Category
import app.cookery.dto.Categories
import app.cookery.dto.MealCategory
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
