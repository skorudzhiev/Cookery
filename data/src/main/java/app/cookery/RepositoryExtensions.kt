package app.cookery

val <T> List<T>.takeTwoRandom: List<T>
    get() = shuffled().take(2)
