package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.daos.EntityDao
import app.cookery.data.entities.categories.ListedMealsByArea
import app.cookery.data.entities.categories.MealsByArea
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MealsByAreaDao : EntityDao<ListedMealsByArea>() {

    @Query("SELECT * from meals_area WHERE mealArea = :mealArea")
    abstract fun mealsByAreaObservable(mealArea: String): Flow<MealsByArea>
}
