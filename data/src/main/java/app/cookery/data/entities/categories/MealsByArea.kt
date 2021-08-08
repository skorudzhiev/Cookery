package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meals_area")
data class MealsByArea(
    @SerializedName("strArea")
    @ColumnInfo(name = "mealArea") val mealArea: String? = null
) : CookeryEntity {
    override val id: Long
        get() = 0
}
