package app.cookery.repositories.categories

import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.categories.CollectionType
import app.cookery.data.entities.categories.MealsCollection
import app.cookery.takeTwoRandom
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesDataSource,
    private val store: CategoriesStore
) {

    fun observeMealsCollection() = store.observeMealsCollection()

    fun observeAllMealCategories() = store.observeAllMealCategories()

    fun observeAreaMeals() = store.observeAreaMeals()

    fun observeMealsFilteredByCategory(category: String) = store.observeMealsFilteredByCategory(category)

    fun observeMealsFilteredByArea(area: String) = store.observeMealsFilteredByArea(area)

    suspend fun fetchAllMealCategories() {
        val response = dataSource.getAllMealCategories().getOrThrow()
        store.saveAllMealCategories(response)
        storeRandomizedMealsCollection(response)
        storeAllMealCategoriesCollection(response)
    }

    private suspend fun storeRandomizedMealsCollection(allMealCategories: AllMealCategories) {
        val categoryDetailsList = emptyList<CategoryDetails>()
        for (category in allMealCategories.categories) {
            category.categoryType?.let { categoryType ->
                val categoryDetails = observeMealsFilteredByCategory(
                    category = categoryType
                )
                categoryDetails.collect { mealsByCategory ->
                    categoryDetailsList.plus(mealsByCategory.meals.takeTwoRandom)
                }
            }
        }
        val collection = MealsCollection(
            // TODO: Replace with string resource
            collectionName = "Popular meals",
            meals = categoryDetailsList,
            type = CollectionType.RandomizedMeals
        )
        store.saveMealsCollection(collection)
    }

    private suspend fun storeAllMealCategoriesCollection(allMealCategories: AllMealCategories) {
        val collection = MealsCollection(
            // TODO: Replace with string resource
            collectionName = "Category meals",
            categories = allMealCategories.categories,
            type = CollectionType.Categories
        )
        store.saveMealsCollection(collection)
    }

    suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        store.saveAreaMeals(response)
        storeRandomizedAreasCollection(response)
        storeAreaMealsCollection(response)
    }

    private suspend fun storeRandomizedAreasCollection(availableAreas: Areas) {
        val categoryDetailsList = emptyList<CategoryDetails>()
        for (area in availableAreas.areas) {
            val categoryDetails = observeMealsFilteredByArea(area = area.mealArea)
            categoryDetails.collect { mealsByArea ->
                categoryDetailsList.plus(mealsByArea.meals.takeTwoRandom)
            }
        }
        val collection = MealsCollection(
            // TODO: Replace with string resource
            collectionName = "Popular meals by area",
            meals = categoryDetailsList,
            type = CollectionType.RandomizedMeals
        )
        store.saveMealsCollection(collection)
    }

    private suspend fun storeAreaMealsCollection(areas: Areas) {
        val collection = MealsCollection(
            // TODO: Replace with string resource
            collectionName = "Available area meals",
            areas = areas.areas,
            type = CollectionType.Areas
        )
        store.saveMealsCollection(collection)
    }

    suspend fun fetchMealsByCategory(category: String) {
        val response = dataSource.getMealsByCategory(category).getOrThrow()
        store.saveMealsByCategory(category, response)
    }

    suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        store.saveMealsByArea(area, response)
    }
}
