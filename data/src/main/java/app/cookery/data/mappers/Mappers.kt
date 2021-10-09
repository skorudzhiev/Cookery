package app.cookery.data.mappers

interface Mapper<F, T> {
    suspend fun map(from: F): T
}
