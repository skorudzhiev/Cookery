package app.cookery.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails

data class FilterMealsByCategory(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "mealId"
    )
    val meals: List<CategoryDetails>
)
