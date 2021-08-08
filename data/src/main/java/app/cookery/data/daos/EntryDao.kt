package app.cookery.data.daos

import app.cookery.Entry

abstract class EntryDao<EC : Entry, LI : EntityDao<EC>>() {
    abstract suspend fun deleteAll()
}
