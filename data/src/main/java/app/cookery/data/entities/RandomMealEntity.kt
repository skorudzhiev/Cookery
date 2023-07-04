package app.cookery.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.data.CookeryEntity

@Entity
data class RandomMealEntity(
    @PrimaryKey
    @ColumnInfo(name = "idMeal") val mealId: String
) : CookeryEntity
