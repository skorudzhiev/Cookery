package app.cookery.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface CookeryEntity
interface CookeryDao<in T> where T : CookeryEntity {

    suspend fun insertDetails(list: List<T>) {
        list.forEach {
            insert(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(details: T)
}
