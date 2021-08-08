package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category_details")
data class CategoryDetails(
    @SerializedName("strMeal")
    @ColumnInfo(name = "strMeal") val mealName: String? = null,

    @SerializedName("strMealThumb")
    @ColumnInfo(name = "categoryImage") val categoryImage: String? = null,

    @PrimaryKey
    @SerializedName("idMeal")
    @ColumnInfo(name = "idMeal") override val id: Long,
) : CookeryEntity {
    companion object {
        val EMPTY = Category(id = 0)
    }
}
