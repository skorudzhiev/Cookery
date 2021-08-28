package com.skorudzhiev.cookery.data

import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory

// www.themealdb.com/api/json/v1/1/lookup.php?i=52772
val mealDetails = MealDetails(
    "", "52772", "Teriyaki Chicken Casserole", "", "Chicken",
    "Japanese", "", "", "", "", "",
    "", "", "", "", "", "",
    "", "", "", "", "", "",
    "", "", "", "", "", "",
    "", "", "", "", "", "", "",
    "", "", "", "", "", "", "",
    "", "", "", "", "", "", "", "", "", "", ""
)

// https://www.themealdb.com/api/json/v1/1/list.php?a=list
val areas = Areas(
    listOf(
        Area(mealArea = "Canadian"),
        Area(mealArea = "Indian"),
        Area(mealArea = "Mexican"),
        Area(mealArea = "")
    )
)

// https://www.themealdb.com/api/json/v1/1/categories.php
val allMealCategories = AllMealCategories(
    listOf(
        Category(
            categoryId = "1",
            categoryType = "Beef",
            categoryImage = "",
            categoryDescription = ""
        ),
        Category(
            categoryId = "2",
            categoryType = "Chicken",
            categoryImage = "",
            categoryDescription = ""
        ),
        Category(
            categoryId = "3",
            categoryType = "Dessert",
            categoryImage = "",
            categoryDescription = ""
        )
    )
)

// https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
val mealsFilteredByArea = FilterMealsByArea(
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
            mealName = "Canadian Butter Tart",
            categoryImage = "",
            mealId = "52923"
        )
    )
)

// https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
val mealsFilteredByCategory = FilterMealsByCategory(
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
