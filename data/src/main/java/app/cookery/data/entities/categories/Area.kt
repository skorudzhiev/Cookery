package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Area(
    @PrimaryKey
    @ColumnInfo(name = "area") val area: String
)
