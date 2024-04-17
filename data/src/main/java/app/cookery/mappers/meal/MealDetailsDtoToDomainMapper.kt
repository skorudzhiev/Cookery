package app.cookery.mappers.meal

import app.cookery.data.Mapper
import app.cookery.domain.model.MealDetails
import app.cookery.dto.MealList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealDetailsDtoToDomainMapper @Inject constructor() : Mapper<MealList, List<MealDetails>> {

    override suspend fun map(from: MealList): List<MealDetails> = from.mealsList.map {
        MealDetails(
            mealId = it.mealId,
            mealName = it.mealName,
            mealCategory = it.mealCategory,
            mealArea = it.mealArea,
            cookingInstructions = it.cookingInstructions,
            mealImage = it.mealImage,
            mealTags = it.mealTags,
            mealYoutube = it.mealYoutube,
            mealIngredient1 = it.mealIngredient1,
            mealIngredient2 = it.mealIngredient2,
            mealIngredient3 = it.mealIngredient3,
            mealIngredient4 = it.mealIngredient4,
            mealIngredient5 = it.mealIngredient5,
            mealIngredient6 = it.mealIngredient6,
            mealIngredient7 = it.mealIngredient7,
            mealIngredient8 = it.mealIngredient8,
            mealIngredient9 = it.mealIngredient9,
            mealIngredient10 = it.mealIngredient10,
            mealIngredient11 = it.mealIngredient11,
            mealIngredient12 = it.mealIngredient12,
            mealIngredient13 = it.mealIngredient13,
            mealIngredient14 = it.mealIngredient14,
            mealIngredient15 = it.mealIngredient15,
            mealIngredient16 = it.mealIngredient16,
            mealIngredient17 = it.mealIngredient17,
            mealIngredient18 = it.mealIngredient18,
            mealIngredient19 = it.mealIngredient19,
            mealIngredient20 = it.mealIngredient20,
            mealMeasure1 = it.mealMeasure1,
            mealMeasure2 = it.mealMeasure2,
            mealMeasure3 = it.mealMeasure3,
            mealMeasure4 = it.mealMeasure4,
            mealMeasure5 = it.mealMeasure5,
            mealMeasure6 = it.mealMeasure6,
            mealMeasure7 = it.mealMeasure7,
            mealMeasure8 = it.mealMeasure8,
            mealMeasure9 = it.mealMeasure9,
            mealMeasure10 = it.mealMeasure10,
            mealMeasure11 = it.mealMeasure11,
            mealMeasure12 = it.mealMeasure12,
            mealMeasure13 = it.mealMeasure13,
            mealMeasure14 = it.mealMeasure14,
            mealMeasure15 = it.mealMeasure15,
            mealMeasure16 = it.mealMeasure16,
            mealMeasure17 = it.mealMeasure17,
            mealMeasure18 = it.mealIngredient18,
            mealMeasure19 = it.mealMeasure19,
            mealMeasure20 = it.mealMeasure20,
        )
    }
}
