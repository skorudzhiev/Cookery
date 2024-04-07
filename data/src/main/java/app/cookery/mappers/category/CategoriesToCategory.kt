package app.cookery.mappers.category

import app.cookery.data.Mapper
import app.cookery.db.entities.categories.CategoryEntity
import app.cookery.dto.Categories
import app.cookery.dto.MealCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesToCategory @Inject constructor(
    private val mapper: MealCategoryToCategory
) : Mapper<Categories, List<CategoryEntity>> {
    override suspend fun map(from: Categories): List<CategoryEntity> = from.categories.map { mapper.map(it) }
}

@Singleton
class MealCategoryToCategory @Inject constructor() : Mapper<MealCategory, CategoryEntity> {
    override suspend fun map(from: MealCategory) = CategoryEntity(
        categoryName = from.categoryName,
        categoryId = from.categoryId,
        categoryImage = from.categoryImage,
        categoryDescription = from.categoryDescription
    )
}
