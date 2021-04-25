package app.cookery

import retrofit2.Call
import retrofit2.http.GET

interface TheMealDbApi {

    @GET("api/json/v1/1/categories.php")
    suspend fun getMealCategories(): Call<List<String>>
}
