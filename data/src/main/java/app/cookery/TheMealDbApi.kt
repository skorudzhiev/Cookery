package app.cookery

import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory
import app.cookery.data.models.Areas
import app.cookery.data.models.Categories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDbApi {

    @GET("/api/json/v1/1/categories.php")
    fun getMealCategories(): Call<Categories>

    @GET("/api/json/v1/1/filter.php")
    fun getMealByCategory(@Query("c") category: String): Call<FilterMealsByCategory>

    @GET("/api/json/v1/1/list.php?a=list")
    fun getMealAreas(): Call<Areas>

    @GET("/api/json/v1/1/filter.php")
    fun getMealByArea(@Query("a") area: String): Call<FilterMealsByArea>

    @GET("/api/json/v1/1/lookup.php")
    fun getMealDetails(@Query("i") mealId: String): Call<MealDetails>
}
