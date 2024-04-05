package com.skorudzhiev.cookery

import app.cookery.db.entities.MealDetails
import app.cookery.db.entities.categories.Area
import app.cookery.db.entities.categories.Category
import app.cookery.db.entities.categories.CategoryDetails

// www.themealdb.com/api/json/v1/1/lookup.php?i=52772
val mealDetails = listOf(
    MealDetails(
        "52772", 0L, "Teriyaki Chicken Casserole", "", "Chicken", "Japanese",
        "", "", "", "", "", "",
        "", "", "", "", "", "",
        "", "", "", "", "", "",
        "", "", "", "", "", "",
        "", "", "", "", "", "", "",
        "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", ""
    )
)

// https://www.themealdb.com/api/json/v1/1/categories.php
val allMealCategories = listOf(
    Category(
        categoryId = "1",
        categoryName = "Beef",
        categoryImage = "",
        categoryDescription = ""
    ),
    Category(
        categoryId = "2",
        categoryName = "Chicken",
        categoryImage = "",
        categoryDescription = ""
    ),
    Category(
        categoryId = "3",
        categoryName = "Dessert",
        categoryImage = "",
        categoryDescription = ""
    )
)

// https://www.themealdb.com/api/json/v1/1/list.php?a=list
val allMealAreas = listOf(
    Area(area = "American"),
    Area(area = "British"),
    Area(area = "Canadian")
)

// https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
val mealsFilteredByArea = listOf(
    CategoryDetails(
        mealId = "52928",
        mealName = "BeaverTails",
        mealImage = "",
        categoryName = "",
        area = "Canadian"
    ),
    CategoryDetails(
        mealId = "52965",
        mealName = "Breakfast Potatoes",
        mealImage = "",
        categoryName = "",
        area = "Canadian"
    ),
    CategoryDetails(
        mealId = "52923",
        mealName = "Canadian Butter Tart",
        mealImage = "",
        categoryName = "",
        area = "Canadian"
    )
)

// https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
val mealsFilteredByCategory = listOf(
    CategoryDetails(
        mealId = "52959",
        mealName = "Baked salmon with fennel & tomatoes",
        mealImage = "",
        categoryName = "Seafood",
        area = ""
    ),
    CategoryDetails(
        mealId = "52819",
        mealName = "Cajun spiced fish tacos",
        mealImage = "",
        categoryName = "Seafood",
        area = ""
    ),
    CategoryDetails(
        mealId = "52944",
        mealName = "Escovitch Fish",
        mealImage = "",
        categoryName = "Seafood",
        area = ""
    )
)
