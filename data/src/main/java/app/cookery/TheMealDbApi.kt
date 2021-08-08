package app.cookery

import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.categories.ListedMealsByArea
import app.cookery.data.entities.categories.ListedMealsByCategory
import app.cookery.data.entities.categories.ListedMealsByFilter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDbApi {

    @GET("/api/json/v1/1/categories.php")
    fun getMealCategories(): Call<ListedMealsByCategory>

    @GET("/api/json/v1/1/filter.php")
    fun getMealByCategory(@Query("c") category: String): Call<ListedMealsByFilter>

    @GET("/api/json/v1/1/list.php?a=list")
    fun getMealAreas(): Call<ListedMealsByArea>

    @GET("/api/json/v1/1/filter.php")
    fun getMealByArea(@Query("a") area: String): Call<ListedMealsByFilter>

    @GET("/api/json/v1/1/lookup.php")
    fun getMealDetails(@Query("i") mealId: String): Call<MealDetails>
}
