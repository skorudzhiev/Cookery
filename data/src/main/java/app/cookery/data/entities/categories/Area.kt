package app.cookery.data.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.CookeryEntity

@Entity
data class Area(
    @PrimaryKey
    @ColumnInfo(name = "area") val area: String
) : CookeryEntity
