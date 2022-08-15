package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.CookeryEntity

@Entity
data class CategoryDetails(
    @PrimaryKey
    @ColumnInfo(name = "mealId") val mealId: String,
    @ColumnInfo(name = "mealName") val mealName: String,
    @ColumnInfo(name = "mealImage") val mealImage: String,
    @ColumnInfo(name = "categoryName") var categoryName: String? = "",
    @ColumnInfo(name = "area") var area: String? = ""
) : CookeryEntity
