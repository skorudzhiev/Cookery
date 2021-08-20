package app.cookery.data.daos

import androidx.room.Dao
import app.cookery.data.entities.MealDetails

@Dao
abstract class MealDao : EntityDao<MealDetails>()
