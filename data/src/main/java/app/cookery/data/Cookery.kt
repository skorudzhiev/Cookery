package app.cookery.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface CookeryEntity

@Dao
interface CookeryDao<in T> where T : CookeryEntity {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(list: List<T>) {
        list.forEach {
            insert(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(details: T)
}
