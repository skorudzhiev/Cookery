package app.cookery.data.entities.categories

import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

data class ListedMealsByFilter(
    @SerializedName("meals")
    val categories: List<CategoryDetails>
) : CookeryEntity {
    override val id: Long
        get() = 0
}
