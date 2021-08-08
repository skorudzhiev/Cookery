package app.cookery.data.entities.categories

import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

data class ListedMealsByCategory(
    override val id: Long,
    @SerializedName("categories")
    val categories: List<Category>

) : CookeryEntity {
    companion object {
        val EMPTY = Category(id = 0)
    }
}
