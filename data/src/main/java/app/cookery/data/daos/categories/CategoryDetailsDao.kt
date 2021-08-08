package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.daos.EntityDao
import app.cookery.data.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CategoryDetailsDao : EntityDao<CategoryDetails>() {

    @Query("SELECT * from category_details WHERE idMeal = :categoryDetailsId")
    abstract fun categoryDetailsWithIdObservable(categoryDetailsId: Int): Flow<CategoryDetails>
}
