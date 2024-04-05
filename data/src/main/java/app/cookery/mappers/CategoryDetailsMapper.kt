package app.cookery.mappers

import app.cookery.db.entities.categories.CategoryDetails
import app.cookery.dto.FilteredCategoryDetails
import app.cookery.dto.MealsCategoryDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsToCategoryDetails @Inject constructor(
    private val mapper: CategoryDetailsMapper
) : Mapper<FilteredCategoryDetails, List<CategoryDetails>> {
    override suspend fun map(from: FilteredCategoryDetails): List<CategoryDetails> {
        return from.mealsList.map { mapper.map(it) }
    }
}

@Singleton
class CategoryDetailsMapper @Inject constructor() : Mapper<MealsCategoryDetails, CategoryDetails> {
    override suspend fun map(from: MealsCategoryDetails) = CategoryDetails(
        mealId = from.mealId,
        mealName = from.mealName,
        mealImage = from.mealImage,
        categoryName = "",
        area = ""
    )
}
