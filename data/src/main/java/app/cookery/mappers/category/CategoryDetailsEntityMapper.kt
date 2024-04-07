package app.cookery.mappers.category

import app.cookery.data.Mapper
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.dto.FilteredCategoryDetails
import app.cookery.dto.MealsCategoryDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsToCategoryDetails @Inject constructor(
    private val mapper: CategoryDetailsEntityMapper
) : Mapper<FilteredCategoryDetails, List<CategoryDetailsEntity>> {
    override suspend fun map(from: FilteredCategoryDetails): List<CategoryDetailsEntity> {
        return from.mealsList.map { mapper.map(it) }
    }
}

@Singleton
class CategoryDetailsEntityMapper @Inject constructor() : Mapper<MealsCategoryDetails, CategoryDetailsEntity> {
    override suspend fun map(from: MealsCategoryDetails) = CategoryDetailsEntity(
        mealId = from.mealId,
        mealName = from.mealName,
        mealImage = from.mealImage,
        categoryName = "",
        area = ""
    )
}
