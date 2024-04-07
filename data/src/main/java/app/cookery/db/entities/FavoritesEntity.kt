package app.cookery.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.db.CookeryEntity

@Entity
data class FavoritesEntity(

    @PrimaryKey
    @ColumnInfo(name = "idMeal") val mealId: String
) : CookeryEntity
