package app.cookery.mappers

interface Mapper<F, T> {
    suspend fun map(from: F): T
}
