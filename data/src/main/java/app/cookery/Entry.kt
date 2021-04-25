package app.cookery

import app.cookery.data.entity.CookeryEntity

interface Entry : CookeryEntity {
    val recipeId: Long
}

interface MultipleEntry : Entry {
    val otherRecipeId: Long
}
