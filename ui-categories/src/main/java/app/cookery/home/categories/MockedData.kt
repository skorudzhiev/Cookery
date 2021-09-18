package app.cookery.home.categories

import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory

// TODO: This will be removed once we get there
object MockedData {
    fun getCategories(): AllMealCategories = allMealCategories
}

// https://www.themealdb.com/api/json/v1/1/categories.php
val allMealCategories = AllMealCategories(
    listOf(
        Category(
            categoryId = "1",
            categoryType = "Beef",
            categoryImage = "https://www.themealdb.com/images/category/beef.png",
            categoryDescription = ""
        ),
        Category(
            categoryId = "2",
            categoryType = "Chicken",
            categoryImage = "https://www.themealdb.com/images/category/chicken.png",
            categoryDescription = ""
        ),
        Category(
            categoryId = "3",
            categoryType = "Dessert",
            categoryImage = "https://www.themealdb.com/images/category/dessert.png",
            categoryDescription = ""
        ),
        Category(
            categoryId = "4",
            categoryType = "Lamb",
            categoryImage = "https://www.themealdb.com/images/category/lamb.png",
            categoryDescription = ""
        )
    )
)

// https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
val seafoodCategory = FilterMealsByCategory(
    category = "Seafood",
    meals = listOf(
        CategoryDetails(
            mealName = "Baked salmon with fennel & tomatoes",
            categoryImage = "",
            mealId = "52959"
        ),
        CategoryDetails(
            mealName = "Cajun spiced fish tacos",
            categoryImage = "",
            mealId = "52819"
        ),
        CategoryDetails(
            mealName = "Escovitch Fish",
            categoryImage = "",
            mealId = "52944"
        )
    )
)

// https://www.themealdb.com/api/json/v1/1/list.php?a=list
val allMealAreas = Areas(
    areas = listOf(
        Area(mealArea = "American"),
        Area(mealArea = "British"),
        Area(mealArea = "Canadian")
    )
)

// https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
val canadianMealsArea = FilterMealsByArea(
    area = "Canadian",
    meals = listOf(
        CategoryDetails(
            mealName = "BeaverTails",
            categoryImage = "",
            mealId = "52928"
        ),
        CategoryDetails(
            mealName = "Breakfast Potatoes",
            categoryImage = "",
            mealId = "52965"
        ),
        CategoryDetails(
            mealName = "Canadian Butter Tarts",
            categoryImage = "",
            mealId = "52923"
        )
    )
)
