package app.cookery.data.models

import com.google.gson.annotations.SerializedName

data class FilteredCategoryDetails(
    @SerializedName("meals") val mealsList: List<MealsCategoryDetails>
)

data class MealsCategoryDetails(
    @SerializedName("strMeal") val mealName: String,
    @SerializedName("strMealThumb") val mealImage: String,
    @SerializedName("idMeal") val mealId: String
)
