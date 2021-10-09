package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.entities.CookeryEntity

@Entity(tableName = "meals_collection")
data class MealsCollection(
    @PrimaryKey
    @ColumnInfo(name = "collectionName")
    val collectionName: String,

    @ColumnInfo(name = "categoryMeals")
    val meals: List<CategoryDetails>? = null,

    @ColumnInfo(name = "categories")
    val categories: List<Category>? = null,

    @ColumnInfo(name = "areas")
    val areas: List<Area>? = null,

    @ColumnInfo(name = "collectionType")
    val type: CollectionType

) : CookeryEntity {
    override val id: Long
        get() = 0
}

enum class CollectionType { Categories, Areas, RandomizedMeals }
