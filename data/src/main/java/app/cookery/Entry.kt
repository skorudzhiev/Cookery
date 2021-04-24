package app.cookery

import app.cookery.entity.CookeryEntity

interface Entry : CookeryEntity {
    val recipeId: Long
}

interface MultipleEntry : Entry {
    val otherRecipeId: Long
}
