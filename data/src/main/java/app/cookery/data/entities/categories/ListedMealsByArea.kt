package app.cookery.data.entities.categories

import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

data class ListedMealsByArea(
    @SerializedName("meals")
    val areas: List<MealsByArea>
) : CookeryEntity {
    override val id: Long
        get() = 0
}
