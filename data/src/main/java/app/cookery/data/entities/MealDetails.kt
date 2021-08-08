package app.cookery.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.entities.categories.Category
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meal_details")
data class MealDetails(
    @SerializedName("meals")
    val meals: String,

    @PrimaryKey
    @SerializedName("idMeal")
    @ColumnInfo(name = "idMeal") override val id: Long = 0,

    @SerializedName("strMeal")
    @ColumnInfo(name = "mealName") val mealName: String? = null,

    @SerializedName("strDrinkAlternate")
    @ColumnInfo(name = "drinkAlternate") val drinkAlternate: String? = null,

    @SerializedName("strCategory")
    @ColumnInfo(name = "mealCategory") val mealCategory: String? = null,

    @SerializedName("strArea")
    @ColumnInfo(name = "mealArea") val mealArea: String? = null,

    @SerializedName("strInstructions")
    @ColumnInfo(name = "cookingInstructions") val cookingInstructions: String? = null,

    @SerializedName("strMealThumb")
    @ColumnInfo(name = "mealImage") val mealImage: String? = null,

    @SerializedName("strTags")
    @ColumnInfo(name = "mealTags") val mealTags: String? = null,

    @SerializedName("strYoutube")
    @ColumnInfo(name = "mealYoutube") val mealYoutube: String? = null,

    @SerializedName("strIngredient1")
    @ColumnInfo(name = "mealIngredient1") val mealIngredient1: String? = null,

    @SerializedName("strIngredient2")
    @ColumnInfo(name = "mealIngredient2") val mealIngredient2: String? = null,

    @SerializedName("strIngredient3")
    @ColumnInfo(name = "mealIngredient3") val mealIngredient3: String? = null,

    @SerializedName("strIngredient4")
    @ColumnInfo(name = "mealIngredient4") val mealIngredient4: String? = null,

    @SerializedName("strIngredient5")
    @ColumnInfo(name = "mealIngredient5") val mealIngredient5: String? = null,

    @SerializedName("strIngredient6")
    @ColumnInfo(name = "mealIngredient6") val mealIngredient6: String? = null,

    @SerializedName("strIngredient7")
    @ColumnInfo(name = "mealIngredient7") val mealIngredient7: String? = null,

    @SerializedName("strIngredient8")
    @ColumnInfo(name = "mealIngredient8") val mealIngredient8: String? = null,

    @SerializedName("strIngredient9")
    @ColumnInfo(name = "mealIngredient9") val mealIngredient9: String? = null,

    @SerializedName("strIngredient10")
    @ColumnInfo(name = "mealIngredient10") val mealIngredient10: String? = null,

    @SerializedName("strIngredient11")
    @ColumnInfo(name = "mealIngredient11") val mealIngredient11: String? = null,

    @SerializedName("strIngredient12")
    @ColumnInfo(name = "mealIngredient12") val mealIngredient12: String? = null,

    @SerializedName("strIngredient13")
    @ColumnInfo(name = "mealIngredient13") val mealIngredient13: String? = null,

    @SerializedName("strIngredient14")
    @ColumnInfo(name = "mealIngredient14") val mealIngredient14: String? = null,

    @SerializedName("strIngredient15")
    @ColumnInfo(name = "mealIngredient15") val mealIngredient15: String? = null,

    @SerializedName("strIngredient16")
    @ColumnInfo(name = "mealIngredient16") val mealIngredient16: String? = null,

    @SerializedName("strIngredient17")
    @ColumnInfo(name = "mealIngredient17") val mealIngredient17: String? = null,

    @SerializedName("strIngredient18")
    @ColumnInfo(name = "mealIngredient18") val mealIngredient18: String? = null,

    @SerializedName("strIngredient19")
    @ColumnInfo(name = "mealIngredient19") val mealIngredient19: String? = null,

    @SerializedName("strIngredient20")
    @ColumnInfo(name = "mealIngredient20") val mealIngredient20: String? = null,

    @SerializedName("strMeasure1")
    @ColumnInfo(name = "mealMeasure1") val mealMeasure1: String? = null,

    @SerializedName("strMeasure2")
    @ColumnInfo(name = "mealMeasure2") val mealMeasure2: String? = null,

    @SerializedName("strMeasure3")
    @ColumnInfo(name = "mealMeasure3") val mealMeasure3: String? = null,

    @SerializedName("strMeasure4")
    @ColumnInfo(name = "mealMeasure4") val mealMeasure4: String? = null,

    @SerializedName("strMeasure5")
    @ColumnInfo(name = "mealMeasure5") val mealMeasure5: String? = null,

    @SerializedName("strMeasure6")
    @ColumnInfo(name = "mealMeasure6") val mealMeasure6: String? = null,

    @SerializedName("strMeasure7")
    @ColumnInfo(name = "mealMeasure7") val mealMeasure7: String? = null,

    @SerializedName("strMeasure8")
    @ColumnInfo(name = "mealMeasure8") val mealMeasure8: String? = null,

    @SerializedName("strMeasure9")
    @ColumnInfo(name = "mealMeasure9") val mealMeasure9: String? = null,

    @SerializedName("strMeasure10")
    @ColumnInfo(name = "mealMeasure10") val mealMeasure10: String? = null,

    @SerializedName("strMeasure11")
    @ColumnInfo(name = "mealMeasure11") val mealMeasure11: String? = null,

    @SerializedName("strMeasure12")
    @ColumnInfo(name = "mealMeasure12") val mealMeasure12: String? = null,

    @SerializedName("strMeasure13")
    @ColumnInfo(name = "mealMeasure13") val mealMeasure13: String? = null,

    @SerializedName("strMeasure14")
    @ColumnInfo(name = "mealMeasure14") val mealMeasure14: String? = null,

    @SerializedName("strMeasure15")
    @ColumnInfo(name = "mealMeasure15") val mealMeasure15: String? = null,

    @SerializedName("strMeasure16")
    @ColumnInfo(name = "mealMeasure16") val mealMeasure16: String? = null,

    @SerializedName("strMeasure17")
    @ColumnInfo(name = "mealMeasure17") val mealMeasure17: String? = null,

    @SerializedName("strMeasure18")
    @ColumnInfo(name = "mealMeasure18") val mealMeasure18: String? = null,

    @SerializedName("strMeasure19")
    @ColumnInfo(name = "mealMeasure19") val mealMeasure19: String? = null,

    @SerializedName("strMeasure20")
    @ColumnInfo(name = "mealMeasure20") val mealMeasure20: String? = null,

    @SerializedName("strSource")
    @ColumnInfo(name = "mealSource") val mealSource: String? = null,

    @SerializedName("strImageSource")
    @ColumnInfo(name = "mealImageSource") val mealImageSource: String? = null,

    @SerializedName("strCreativeCommonsConfirmed")
    @ColumnInfo(name = "mealCreativeCommonsConfirmed") val mealCreativeCommonsConfirmed: String? = null,

    @SerializedName("dateModified")
    @ColumnInfo(name = "dateModified") val dateModified: String? = null,
) : CookeryEntity {
    companion object {
        val EMPTY = Category(id = 0)
    }
}
