package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meal_categories")
data class Category(
    @PrimaryKey
    @SerializedName("idCategory")
    @ColumnInfo(name = "idCategory") override val id: Long = 0,

    @SerializedName("strCategory")
    @ColumnInfo(name = "categoryType") val categoryType: String? = null,

    @SerializedName("strCategoryThumb")
    @ColumnInfo(name = "categoryImage") val categoryImage: String? = null,

    @SerializedName("strCategoryDescription")
    @ColumnInfo(name = "categoryDescription") val categoryDescription: String? = null,
) : CookeryEntity {
    companion object {
        val EMPTY = Category(id = 0)
    }
}
