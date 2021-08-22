package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import app.cookery.data.entities.CookeryEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "areas")
data class Areas(
    @PrimaryKey
    @SerializedName("meals")
    @ColumnInfo(name = "areas")
    val areas: List<Area>
) : CookeryEntity {
    override val id: Long
        get() = 0
}

class AreasTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<Area> {
        val listType = object : TypeToken<List<Area>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Area?>): String {
        return Gson().toJson(list)
    }
}
