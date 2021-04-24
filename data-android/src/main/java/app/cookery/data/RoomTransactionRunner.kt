package app.cookery.data

import androidx.room.withTransaction
import app.cookery.DatabaseTransactionRunner
import javax.inject.Inject

class RoomTransactionRunner @Inject constructor(
    private val db: CookeryRoomDatabase
) : DatabaseTransactionRunner {
    override suspend fun <T> invoke(block: suspend () -> T): T {
        return db.withTransaction {
            block()
        }
    }
}
