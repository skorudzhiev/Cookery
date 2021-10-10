package app.cookery.domain.observers

import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.relations.FilterMealsByCategory
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ObserveMealsByCategory @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveMealsByCategory.Params, FilterMealsByCategory>() {

    override fun createObservable(params: Params): Flow<FilterMealsByCategory> {
        return flowOf(
            FilterMealsByCategory(
                category = Category(
                    categoryName = "",
                    categoryId = "",
                    categoryImage = null,
                    categoryDescription = null
                ),
                meals = listOf()
            )
        )
    }

    data class Params(val category: String)
}
