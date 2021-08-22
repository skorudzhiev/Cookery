package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import app.cookery.data.entities.CookeryEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "categories")
data class AllMealCategories(
    @PrimaryKey
    @SerializedName("categories")
    @ColumnInfo(name = "categories")
    val categories: List<Category>
) : CookeryEntity {
    override val id: Long
        get() = 0
}

class CategoriesTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<Category> {
        val listType = object : TypeToken<List<Category>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Category?>): String {
        return Gson().toJson(list)
    }
}
