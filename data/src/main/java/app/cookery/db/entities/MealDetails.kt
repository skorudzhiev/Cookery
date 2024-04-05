package app.cookery.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.db.CookeryEntity

@Entity
data class MealDetails(
    @PrimaryKey
    @ColumnInfo(name = "idMeal") val mealId: String = "",
    @ColumnInfo(name = "insertedAt") val insertedAt: Long? = null,
    @ColumnInfo(name = "mealName") val mealName: String? = null,
    @ColumnInfo(name = "drinkAlternate") val drinkAlternate: String? = null,
    @ColumnInfo(name = "mealCategory") val mealCategory: String? = null,
    @ColumnInfo(name = "mealArea") val mealArea: String? = null,
    @ColumnInfo(name = "cookingInstructions") val cookingInstructions: String? = null,
    @ColumnInfo(name = "mealImage") val mealImage: String? = null,
    @ColumnInfo(name = "mealTags") val mealTags: String? = null,
    @ColumnInfo(name = "mealYoutube") val mealYoutube: String? = null,
    @ColumnInfo(name = "mealIngredient1") val mealIngredient1: String? = null,
    @ColumnInfo(name = "mealIngredient2") val mealIngredient2: String? = null,
    @ColumnInfo(name = "mealIngredient3") val mealIngredient3: String? = null,
    @ColumnInfo(name = "mealIngredient4") val mealIngredient4: String? = null,
    @ColumnInfo(name = "mealIngredient5") val mealIngredient5: String? = null,
    @ColumnInfo(name = "mealIngredient6") val mealIngredient6: String? = null,
    @ColumnInfo(name = "mealIngredient7") val mealIngredient7: String? = null,
    @ColumnInfo(name = "mealIngredient8") val mealIngredient8: String? = null,
    @ColumnInfo(name = "mealIngredient9") val mealIngredient9: String? = null,
    @ColumnInfo(name = "mealIngredient10") val mealIngredient10: String? = null,
    @ColumnInfo(name = "mealIngredient11") val mealIngredient11: String? = null,
    @ColumnInfo(name = "mealIngredient12") val mealIngredient12: String? = null,
    @ColumnInfo(name = "mealIngredient13") val mealIngredient13: String? = null,
    @ColumnInfo(name = "mealIngredient14") val mealIngredient14: String? = null,
    @ColumnInfo(name = "mealIngredient15") val mealIngredient15: String? = null,
    @ColumnInfo(name = "mealIngredient16") val mealIngredient16: String? = null,
    @ColumnInfo(name = "mealIngredient17") val mealIngredient17: String? = null,
    @ColumnInfo(name = "mealIngredient18") val mealIngredient18: String? = null,
    @ColumnInfo(name = "mealIngredient19") val mealIngredient19: String? = null,
    @ColumnInfo(name = "mealIngredient20") val mealIngredient20: String? = null,
    @ColumnInfo(name = "mealMeasure1") val mealMeasure1: String? = null,
    @ColumnInfo(name = "mealMeasure2") val mealMeasure2: String? = null,
    @ColumnInfo(name = "mealMeasure3") val mealMeasure3: String? = null,
    @ColumnInfo(name = "mealMeasure4") val mealMeasure4: String? = null,
    @ColumnInfo(name = "mealMeasure5") val mealMeasure5: String? = null,
    @ColumnInfo(name = "mealMeasure6") val mealMeasure6: String? = null,
    @ColumnInfo(name = "mealMeasure7") val mealMeasure7: String? = null,
    @ColumnInfo(name = "mealMeasure8") val mealMeasure8: String? = null,
    @ColumnInfo(name = "mealMeasure9") val mealMeasure9: String? = null,
    @ColumnInfo(name = "mealMeasure10") val mealMeasure10: String? = null,
    @ColumnInfo(name = "mealMeasure11") val mealMeasure11: String? = null,
    @ColumnInfo(name = "mealMeasure12") val mealMeasure12: String? = null,
    @ColumnInfo(name = "mealMeasure13") val mealMeasure13: String? = null,
    @ColumnInfo(name = "mealMeasure14") val mealMeasure14: String? = null,
    @ColumnInfo(name = "mealMeasure15") val mealMeasure15: String? = null,
    @ColumnInfo(name = "mealMeasure16") val mealMeasure16: String? = null,
    @ColumnInfo(name = "mealMeasure17") val mealMeasure17: String? = null,
    @ColumnInfo(name = "mealMeasure18") val mealMeasure18: String? = null,
    @ColumnInfo(name = "mealMeasure19") val mealMeasure19: String? = null,
    @ColumnInfo(name = "mealMeasure20") val mealMeasure20: String? = null,
    @ColumnInfo(name = "mealSource") val mealSource: String? = null,
    @ColumnInfo(name = "mealImageSource") val mealImageSource: String? = null,
    @ColumnInfo(name = "mealCreativeCommonsConfirmed") val mealCreativeCommonsConfirmed: String? = null,
    @ColumnInfo(name = "dateModified") val dateModified: String? = null
) : CookeryEntity

data class Ingredient(
    val measure: String?,
    val ingredient: String?
)

fun getIngredients(mealDetails: MealDetails?): List<Ingredient?> {
    return listOf(
        Ingredient(
            measure = mealDetails?.mealMeasure1,
            ingredient = mealDetails?.mealIngredient1
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure2,
            ingredient = mealDetails?.mealIngredient2
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure3,
            ingredient = mealDetails?.mealIngredient3
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure4,
            ingredient = mealDetails?.mealIngredient4
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure5,
            ingredient = mealDetails?.mealIngredient5
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure6,
            ingredient = mealDetails?.mealIngredient6
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure7,
            ingredient = mealDetails?.mealIngredient7
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure8,
            ingredient = mealDetails?.mealIngredient8
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure9,
            ingredient = mealDetails?.mealIngredient9
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure10,
            ingredient = mealDetails?.mealIngredient10
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure11,
            ingredient = mealDetails?.mealIngredient11
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure12,
            ingredient = mealDetails?.mealIngredient12
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure13,
            ingredient = mealDetails?.mealIngredient13
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure14,
            ingredient = mealDetails?.mealIngredient14
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure15,
            ingredient = mealDetails?.mealIngredient15
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure16,
            ingredient = mealDetails?.mealIngredient16,

        ),
        Ingredient(
            measure = mealDetails?.mealMeasure17,
            ingredient = mealDetails?.mealIngredient17
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure18,
            ingredient = mealDetails?.mealIngredient18
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure19,
            ingredient = mealDetails?.mealIngredient19
        ),
        Ingredient(
            measure = mealDetails?.mealMeasure20,
            ingredient = mealDetails?.mealIngredient20
        )
    )
}
