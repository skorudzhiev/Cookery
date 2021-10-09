package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "categoryName") val categoryName: String,
    @ColumnInfo(name = "categoryId") val categoryId: String = "",
    @ColumnInfo(name = "categoryImage") val categoryImage: String? = null,
    @ColumnInfo(name = "categoryDescription") val categoryDescription: String? = null
)
