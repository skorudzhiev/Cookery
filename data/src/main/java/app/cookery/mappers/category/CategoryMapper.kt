package app.cookery.mappers.category

import app.cookery.data.Mapper
import app.cookery.db.entities.categories.CategoryEntity
import app.cookery.domain.model.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryMapper @Inject constructor() : Mapper<CategoryEntity, Category> {

    override suspend fun map(from: CategoryEntity) = Category(
        categoryName = from.categoryName,
        categoryId = from.categoryId,
        categoryImage = from.categoryImage,
        categoryDescription = from.categoryDescription
    )
}
