package app.cookery.data.entities.categories

import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

data class ListedMealsByCategory(
    @SerializedName("categories")
    val categories: List<Category>
) : CookeryEntity {
    override val id: Long
        get() = 0
}
