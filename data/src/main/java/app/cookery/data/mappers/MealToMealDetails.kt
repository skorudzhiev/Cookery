package app.cookery.data.mappers

import app.cookery.data.entities.MealDetails
import app.cookery.data.models.Meal
import app.cookery.data.models.MealList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealListToMealDetails @Inject constructor(
    private val mapper: MealToMealDetails
) : Mapper<MealList, List<MealDetails>> {
    override suspend fun map(from: MealList): List<MealDetails> = from.mealsList.map { mapper.map(it) }
}

@Singleton
class MealToMealDetails @Inject constructor() : Mapper<Meal, MealDetails> {
    override suspend fun map(from: Meal): MealDetails = MealDetails(
        mealId = from.mealId,
        mealName = from.mealName,
        drinkAlternate = from.drinkAlternate,
        mealCategory = from.mealCategory,
        mealArea = from.mealArea,
        cookingInstructions = from.cookingInstructions,
        mealImage = from.mealImage,
        mealTags = from.mealTags,
        mealYoutube = from.mealYoutube,
        mealIngredient1 = from.mealIngredient1,
        mealIngredient2 = from.mealIngredient2,
        mealIngredient3 = from.mealIngredient3,
        mealIngredient4 = from.mealIngredient4,
        mealIngredient5 = from.mealIngredient5,
        mealIngredient6 = from.mealIngredient6,
        mealIngredient7 = from.mealIngredient7,
        mealIngredient8 = from.mealIngredient8,
        mealIngredient9 = from.mealIngredient9,
        mealIngredient10 = from.mealIngredient10,
        mealIngredient11 = from.mealIngredient11,
        mealIngredient12 = from.mealIngredient12,
        mealIngredient13 = from.mealIngredient13,
        mealIngredient14 = from.mealIngredient14,
        mealIngredient15 = from.mealIngredient15,
        mealIngredient16 = from.mealIngredient16,
        mealIngredient17 = from.mealIngredient17,
        mealIngredient18 = from.mealIngredient18,
        mealIngredient19 = from.mealIngredient19,
        mealIngredient20 = from.mealIngredient20,
        mealMeasure1 = from.mealMeasure1,
        mealMeasure2 = from.mealMeasure2,
        mealMeasure3 = from.mealMeasure3,
        mealMeasure4 = from.mealMeasure4,
        mealMeasure5 = from.mealMeasure5,
        mealMeasure6 = from.mealMeasure6,
        mealMeasure7 = from.mealMeasure7,
        mealMeasure8 = from.mealMeasure8,
        mealMeasure9 = from.mealMeasure9,
        mealMeasure10 = from.mealMeasure10,
        mealMeasure11 = from.mealMeasure11,
        mealMeasure12 = from.mealMeasure12,
        mealMeasure13 = from.mealMeasure13,
        mealMeasure14 = from.mealMeasure14,
        mealMeasure15 = from.mealMeasure15,
        mealMeasure16 = from.mealMeasure16,
        mealMeasure17 = from.mealMeasure17,
        mealMeasure18 = from.mealMeasure18,
        mealMeasure19 = from.mealMeasure19,
        mealMeasure20 = from.mealMeasure20,
        mealSource = from.mealSource,
        mealImageSource = from.mealImageSource,
        mealCreativeCommonsConfirmed = from.mealCreativeCommonsConfirmed,
        dateModified = from.dateModified
    )
}
