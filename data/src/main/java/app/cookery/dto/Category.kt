package app.cookery.dto

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("categories")
    val categories: List<MealCategory>
)

data class MealCategory(
    @SerializedName("idCategory") val categoryId: String,
    @SerializedName("strCategory") val categoryName: String,
    @SerializedName("strCategoryThumb") val categoryImage: String,
    @SerializedName("strCategoryDescription") val categoryDescription: String
)
