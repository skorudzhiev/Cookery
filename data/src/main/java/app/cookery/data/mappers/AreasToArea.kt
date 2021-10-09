package app.cookery.data.mappers

import app.cookery.data.entities.categories.Area
import app.cookery.data.models.Areas
import app.cookery.data.models.MealsArea
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreasToArea @Inject constructor(
    private val mapper: MealsAreaToArea
) : Mapper<Areas, List<Area>> {
    override suspend fun map(from: Areas): List<Area> = from.areas.map { mapper.map(it) }
}

@Singleton
class MealsAreaToArea @Inject constructor() : Mapper<MealsArea, Area> {
    override suspend fun map(from: MealsArea): Area = Area(area = from.area)
}
