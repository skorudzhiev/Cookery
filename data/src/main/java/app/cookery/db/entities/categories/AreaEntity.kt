package app.cookery.db.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.db.CookeryEntity

@Entity
data class AreaEntity(
    @PrimaryKey
    @ColumnInfo(name = "area") val area: String
) : CookeryEntity
