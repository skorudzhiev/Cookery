package app.cookery.db.daos.search

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.search.RecentSearchesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchesDao : CookeryDao<RecentSearchesEntity> {

    @Query("SELECT * FROM RecentSearchesEntity")
    fun getRecentSearches(): Flow<List<RecentSearchesEntity>>

    @Query("DELETE FROM RecentSearchesEntity")
    suspend fun clearRecentSearches()
}
