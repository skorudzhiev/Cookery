package com.skorudzhiev.cookery

import app.cookery.db.entities.MealDetailsEntity
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.db.entities.categories.CategoryEntity

// www.themealdb.com/api/json/v1/1/lookup.php?i=52772
val mealDetailEntities = listOf(
    MealDetailsEntity(
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
    CategoryEntity(
        categoryId = "1",
        categoryName = "Beef",
        categoryImage = "",
        categoryDescription = ""
    ),
    CategoryEntity(
        categoryId = "2",
        categoryName = "Chicken",
        categoryImage = "",
        categoryDescription = ""
    ),
    CategoryEntity(
        categoryId = "3",
        categoryName = "Dessert",
        categoryImage = "",
        categoryDescription = ""
    )
)

// https://www.themealdb.com/api/json/v1/1/list.php?a=list
val allMealAreaEntities = listOf(
    AreaEntity(area = "American"),
    AreaEntity(area = "British"),
    AreaEntity(area = "Canadian")
)

// https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
val mealsFilteredByArea = listOf(
    CategoryDetailsEntity(
        mealId = "52928",
        mealName = "BeaverTails",
        mealImage = "",
        categoryName = "",
        area = "Canadian"
    ),
    CategoryDetailsEntity(
        mealId = "52965",
        mealName = "Breakfast Potatoes",
        mealImage = "",
        categoryName = "",
        area = "Canadian"
    ),
    CategoryDetailsEntity(
        mealId = "52923",
        mealName = "Canadian Butter Tart",
        mealImage = "",
        categoryName = "",
        area = "Canadian"
    )
)

// https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
val mealsFilteredByCategory = listOf(
    CategoryDetailsEntity(
        mealId = "52959",
        mealName = "Baked salmon with fennel & tomatoes",
        mealImage = "",
        categoryName = "Seafood",
        area = ""
    ),
    CategoryDetailsEntity(
        mealId = "52819",
        mealName = "Cajun spiced fish tacos",
        mealImage = "",
        categoryName = "Seafood",
        area = ""
    ),
    CategoryDetailsEntity(
        mealId = "52944",
        mealName = "Escovitch Fish",
        mealImage = "",
        categoryName = "Seafood",
        area = ""
    )
)
