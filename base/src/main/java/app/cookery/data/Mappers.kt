package app.cookery.data

interface Mapper<F, T> {
    suspend fun map(from: F): T
}
