package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.entities.CookeryEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "filtered_by_area")
data class FilterMealsByArea(
    @PrimaryKey
    @ColumnInfo(name = "area")
    var area: String,

    @SerializedName("meals")
    @ColumnInfo(name = "meals")
    val meals: List<CategoryDetails>
) : CookeryEntity {
    override val id: Long
        get() = 0
}
