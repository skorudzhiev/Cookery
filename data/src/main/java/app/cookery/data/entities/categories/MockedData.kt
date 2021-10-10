package app.cookery.data.entities.categories

// TODO: This will be removed once we get there

object MockedData {
    fun getMealsCollection(): List<MealCollection> = mealsCollection
}

// Immutable from compose
data class MealCollection(
    val name: String,
    val meals: List<CategoryDetails>? = null,
    val categories: List<Category>? = null,
    val areas: List<Area>? = null,
    val type: CollectionType
)

// TODO: Categories
// https://www.themealdb.com/api/json/v1/1/categories.php
val allMealCategories = listOf(
    Category(
        categoryId = "1",
        categoryName = "Beef",
        categoryImage = "https://www.themealdb.com/images/category/beef.png",
        categoryDescription = ""
    ),
    Category(
        categoryId = "2",
        categoryName = "Chicken",
        categoryImage = "https://www.themealdb.com/images/category/chicken.png",
        categoryDescription = ""
    ),
    Category(
        categoryId = "3",
        categoryName = "Dessert",
        categoryImage = "https://www.themealdb.com/images/category/dessert.png",
        categoryDescription = ""
    ),
    Category(
        categoryId = "4",
        categoryName = "Lamb",
        categoryImage = "https://www.themealdb.com/images/category/lamb.png",
        categoryDescription = ""
    ),
    Category(
        categoryId = "5",
        categoryName = "Pasta",
        categoryImage = "https://www.themealdb.com/images/category/pasta.png",
        categoryDescription = ""
    )
)

// This is just for debug
val randomMealsFromCategories = (
    allMealCategories.shuffled().take(2)
    )

// https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef
val beefCategory = listOf(
    CategoryDetails(
        mealName = "Beef and Mustard Pie",
        mealImage = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
        mealId = "52874"
    ),
    CategoryDetails(
        mealName = "Beef and Oyster pie",
        mealImage = "https://www.themealdb.com/images/media/meals/wrssvt1511556563.jpg",
        mealId = "52878"
    ),
    CategoryDetails(
        mealName = "Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
        mealImage = "https://www.themealdb.com/images/media/meals/z0ageb1583189517.jpg",
        mealId = "52997"
    ),
    CategoryDetails(
        mealName = "Beef Bourguignon",
        mealImage = "https://www.themealdb.com/images/media/meals/vtqxtu1511784197.jpg",
        mealId = "52904"
    ),
)

val chickenCategory = listOf(
    CategoryDetails(
        mealName = "Brown Stew Chicken",
        mealImage = "https://www.themealdb.com/images/media/meals/sypxpx1515365095.jpg",
        mealId = "52940"
    ),
    CategoryDetails(
        mealName = "Chick-Fil-A Sandwich",
        mealImage = "https://www.themealdb.com/images/media/meals/sbx7n71587673021.jpg",
        mealId = "53016"
    ),
    CategoryDetails(
        mealName = "Chicken & mushroom Hotpot",
        mealImage = "https://www.themealdb.com/images/media/meals/uuuspp1511297945.jpg",
        mealId = "52846"
    ),
    CategoryDetails(
        mealName = "Chicken Alfredo Primavera",
        mealImage = "https://www.themealdb.com/images/media/meals/syqypv1486981727.jpg",
        mealId = "52796"
    ),
)

val dessertCategory = listOf(
    CategoryDetails(
        mealName = "Apple & Blackberry Crumble",
        mealImage = "https://www.themealdb.com/images/media/meals/xvsurr1511719182.jpg",
        mealId = "52893"
    ),
    CategoryDetails(
        mealName = "Banana Pancakes",
        mealImage = "https://www.themealdb.com/images/media/meals/sywswr1511383814.jpg",
        mealId = "52855"
    ),
    CategoryDetails(
        mealName = "Bread and Butter Pudding",
        mealImage = "https://www.themealdb.com/images/media/meals/xqwwpy1483908697.jpg",
        mealId = "52792"
    ),
    CategoryDetails(
        mealName = "Cashew Ghoriba Biscuits",
        mealImage = "https://www.themealdb.com/images/media/meals/t3r3ka1560461972.jpg",
        mealId = "52976"
    ),
)

val lambCategory = listOf(
    CategoryDetails(
        mealName = "Lamb and Lemon Souvlaki",
        mealImage = "https://www.themealdb.com/images/media/meals/rjhf741585564676.jpg",
        mealId = "53009"
    ),
    CategoryDetails(
        mealName = "Lamb tomato and sweet spices",
        mealImage = "https://www.themealdb.com/images/media/meals/qtwtss1468572261.jpg",
        mealId = "52782"
    ),
    CategoryDetails(
        mealName = "Lamb Tzatziki Burgers",
        mealImage = "https://www.themealdb.com/images/media/meals/k420tj1585565244.jpg",
        mealId = "53010"
    ),
    CategoryDetails(
        mealName = "Rigatoni with fennel sausage sauce",
        mealImage = "https://www.themealdb.com/images/media/meals/qtqvys1468573168.jpg",
        mealId = "52783"
    ),
)

val pastaCategory = listOf(
    CategoryDetails(
        mealName = "Spaghetti alla Carbonara",
        mealImage = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
        mealId = "52982"
    ),
    CategoryDetails(
        mealName = "Lasagne",
        mealImage = "https://www.themealdb.com/images/media/meals/wtsvxx1511296896.jpg",
        mealId = "52844"
    ),
    CategoryDetails(
        mealName = "Fettucine alfredo",
        mealImage = "https://www.themealdb.com/images/media/meals/uquqtu1511178042.jpg",
        mealId = "52835"
    ),
    CategoryDetails(
        mealName = "Pilchard puttanesca",
        mealImage = "https://www.themealdb.com/images/media/meals/vvtvtr1511180578.jpg",
        mealId = "52837"
    ),
)

val randomCategoryMealsList = takeTwoRandomMeals(beefCategory)
    .plus(takeTwoRandomMeals(chickenCategory))
    .plus(takeTwoRandomMeals(dessertCategory))
    .plus(takeTwoRandomMeals(lambCategory))
    .plus(takeTwoRandomMeals(pastaCategory))

// TODO: Areas
// https://www.themealdb.com/api/json/v1/1/list.php?a=list
val allMealAreas =
    listOf(
        Area(area = "American"),
        Area(area = "British"),
        Area(area = "Canadian"),
        Area(area = "Chinese"),
        Area(area = "French")
    )

// https://www.themealdb.com/api/json/v1/1/filter.php?a=American
val americanMealsArea = listOf(
    CategoryDetails(
        mealName = "Banana Pancakes",
        mealImage = "https://www.themealdb.com/images/media/meals/sywswr1511383814.jpg",
        mealId = "52855"
    ),
    CategoryDetails(
        mealName = "BBQ Pork Sloppy Joes",
        mealImage = "https://www.themealdb.com/images/media/meals/atd5sh1583188467.jpg",
        mealId = "52995"
    ),
    CategoryDetails(
        mealName = "Beef Brisket Pot Roast",
        mealImage = "https://www.themealdb.com/images/media/meals/ursuup1487348423.jpg",
        mealId = "52812"
    ),
    CategoryDetails(
        mealName = "Big Mac",
        mealImage = "https://www.themealdb.com/images/media/meals/urzj1d1587670726.jpg",
        mealId = "53013"
    )
)

val britishMealsArea = listOf(
    CategoryDetails(
        mealName = "Bubble & Squeak",
        mealImage = "https://www.themealdb.com/images/media/meals/xusqvw1511638311.jpg",
        mealId = "52885"
    ),
    CategoryDetails(
        mealName = "Apple & Blackberry Crumble",
        mealImage = "https://www.themealdb.com/images/media/meals/xvsurr1511719182.jpg",
        mealId = ""
    ),
    CategoryDetails(
        mealName = "Baked salmon with fennel & tomatoes",
        mealImage = "https://www.themealdb.com/images/media/meals/1548772327.jpg",
        mealId = ""
    ),
    CategoryDetails(
        mealName = "Bakewell tart",
        mealImage = "https://www.themealdb.com/images/media/meals/wyrqqq1468233628.jpg",
        mealId = ""
    )
)

val canadianMealsArea = listOf(
    CategoryDetails(
        mealName = "BeaverTails",
        mealImage = "https://www.themealdb.com/images/media/meals/ryppsv1511815505.jpg",
        mealId = "52928"
    ),
    CategoryDetails(
        mealName = "Breakfast Potatoes",
        mealImage = "https://www.themealdb.com/images/media/meals/1550441882.jpg",
        mealId = "52965"
    ),
    CategoryDetails(
        mealName = "Canadian Butter Tarts",
        mealImage = "https://www.themealdb.com/images/media/meals/wpputp1511812960.jpg",
        mealId = "52923"
    ),
    CategoryDetails(
        mealName = "Montreal Smoked Meat",
        mealImage = "https://www.themealdb.com/images/media/meals/uttupv1511815050.jpg",
        mealId = "52927"
    )
)

val chineseMealsArea = listOf(
    CategoryDetails(
        mealName = "Beef Lo Mein",
        mealImage = "https://www.themealdb.com/images/media/meals/1529444830.jpg",
        mealId = ""
    ),
    CategoryDetails(
        mealName = "Chicken Congee",
        mealImage = "https://www.themealdb.com/images/media/meals/1529446352.jpg",
        mealId = ""
    ),
    CategoryDetails(
        mealName = "Egg Drop Soup",
        mealImage = "https://www.themealdb.com/images/media/meals/1529446137.jpg",
        mealId = ""
    ),
    CategoryDetails(
        mealName = "General Tso's Chicken",
        mealImage = "https://www.themealdb.com/images/media/meals/1529444113.jpg",
        mealId = ""
    )
)

val randomAreaMealsList = takeTwoRandomMeals(americanMealsArea)
    .plus(takeTwoRandomMeals(britishMealsArea))
    .plus(takeTwoRandomMeals(canadianMealsArea))
    .plus(takeTwoRandomMeals(chineseMealsArea))

val mealsCollection = listOf(
    MealCollection(
        name = "Popular meals",
        meals = randomCategoryMealsList,
        type = CollectionType.RandomizedMeals
    ),
    MealCollection(
        name = "Category meals",
        categories = allMealCategories,
        type = CollectionType.Categories
    ),
    MealCollection(
        name = "Popular meals by area",
        meals = randomAreaMealsList,
        type = CollectionType.RandomizedMeals
    ),
    MealCollection(
        name = "Available area meals",
        areas = allMealAreas,
        type = CollectionType.Areas
    )
)

private fun takeTwoRandomMeals(meals: List<CategoryDetails>): List<CategoryDetails> {
    return meals.shuffled().take(2)
}
