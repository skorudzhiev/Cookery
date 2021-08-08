package app.cookery

import app.cookery.data.entities.CookeryEntity

interface Entry : CookeryEntity {
    val recipeId: Int
}

interface MultipleEntry : Entry {
    val otherRecipeId: Int
}
