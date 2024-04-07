package app.cookery.mappers.area

import app.cookery.data.Mapper
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.dto.Areas
import app.cookery.dto.MealsArea
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreasToArea @Inject constructor(
    private val mapper: AreaEntityMapper
) : Mapper<Areas, List<AreaEntity>> {
    override suspend fun map(from: Areas): List<AreaEntity> = from.areas.map { mapper.map(it) }
}

@Singleton
class AreaEntityMapper @Inject constructor() : Mapper<MealsArea, AreaEntity> {
    override suspend fun map(from: MealsArea): AreaEntity = AreaEntity(area = from.area)
}
