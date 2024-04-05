package app.cookery.dto

import com.google.gson.annotations.SerializedName

data class Areas(
    @SerializedName("meals")
    val areas: List<MealsArea>
)

data class MealsArea(
    @SerializedName("strArea")
    val area: String
)
