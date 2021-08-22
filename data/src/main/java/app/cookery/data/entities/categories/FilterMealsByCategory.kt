package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import app.cookery.data.entities.CookeryEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "filtered_by_category")
data class FilterMealsByCategory(
    @PrimaryKey
    @ColumnInfo(name = "category")
    var category: String,

    @SerializedName("meals")
    @ColumnInfo(name = "meals")
    val meals: List<CategoryDetails>
) : CookeryEntity {
    override val id: Long
        get() = 0
}

class CategoryDetailsTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<CategoryDetails> {
        val listType = object : TypeToken<List<CategoryDetails>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<CategoryDetails?>): String {
        return Gson().toJson(list)
    }
}
