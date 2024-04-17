package app.cookery.db.entities.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.cookery.db.CookeryEntity

@Entity
data class RecentSearchesEntity(

    @PrimaryKey
    @ColumnInfo(name = "searchQuery") val searchQuery: String
) : CookeryEntity
