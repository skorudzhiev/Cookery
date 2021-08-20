package app.cookery.data.daos.categories

import androidx.room.Dao
import app.cookery.data.daos.EntityDao
import app.cookery.data.entities.categories.ListedMealsByFilter

@Dao
abstract class MealsByFilterDao : EntityDao<ListedMealsByFilter>()
