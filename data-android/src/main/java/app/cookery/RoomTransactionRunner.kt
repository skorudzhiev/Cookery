package app.cookery

import androidx.room.withTransaction
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
