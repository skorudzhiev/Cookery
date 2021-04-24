package app.cookery.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.cookery.CookeryDatabase

@Database(
    entities = [
    ],
    version = 1
)
abstract class CookeryRoomDatabase : RoomDatabase(), CookeryDatabase
