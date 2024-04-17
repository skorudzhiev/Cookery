package app.cookery

import app.cookery.dto.Areas
import app.cookery.dto.Categories
import app.cookery.dto.FilteredCategoryDetails
import app.cookery.dto.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDbApi {

    @GET("/api/json/v1/1/categories.php")
    fun getMealCategories(): Call<Categories>

    @GET("/api/json/v1/1/filter.php")
    fun getMealByCategory(@Query("c") category: String): Call<FilteredCategoryDetails>

    @GET("/api/json/v1/1/list.php?a=list")
    fun getMealAreas(): Call<Areas>

    @GET("/api/json/v1/1/filter.php")
    fun getMealByArea(@Query("a") area: String): Call<FilteredCategoryDetails>

    @GET("/api/json/v1/1/lookup.php")
    fun getMealDetails(@Query("i") mealId: String): Call<MealList>

    @GET("/api/json/v1/1/random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("/api/json/v1/1/search.php")
    fun searchMealByName(@Query("s") mealName: String): Call<MealList>
}
