package app.cookery.mappers.category

import app.cookery.data.Mapper
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.domain.model.CategoryDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryDetailsMapper @Inject constructor() : Mapper<CategoryDetailsEntity, CategoryDetails> {

    override suspend fun map(from: CategoryDetailsEntity) = CategoryDetails(
        mealId = from.mealId,
        mealName = from.mealName,
        mealImage = from.mealImage,
        categoryName = from.categoryName,
        area = from.area
    )
}
