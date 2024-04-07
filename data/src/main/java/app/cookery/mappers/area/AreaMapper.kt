package app.cookery.mappers.area

import app.cookery.data.Mapper
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.domain.model.Area
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreaMapper @Inject constructor() : Mapper<AreaEntity, Area> {

    override suspend fun map(from: AreaEntity) = Area(
        area = from.area
    )
}
