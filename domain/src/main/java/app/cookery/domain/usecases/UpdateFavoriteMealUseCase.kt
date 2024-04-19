package app.cookery.domain.usecases

import app.cookery.domain.repositories.MealRepository
import javax.inject.Inject

class UpdateFavoriteMealUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(mealId: String, isMarkedAsFavorite: Boolean) {
        repository.updateFavoriteMeal(
            mealId = mealId,
            isFavorite = isMarkedAsFavorite
        )
    }
}
