package com.skorudzhiev.cookery.buildsrc

object Versions {
    const val ktlint = "0.40.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:8.3.0"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val mockwebserver = "com.squareup.okhttp3:mockwebserver:4.1.0"
    const val mock = "io.mockk:mockk:1.9.3"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.3.1"

    object Kotlin {
        private const val version = "1.9.23"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0-alpha02"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha03"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
        const val activityCompose = "androidx.activity:activity-compose:1.3.1"
        const val navigationCompose = "androidx.navigation:navigation-compose:2.5.3"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
        const val datastorePrefs = "androidx.datastore:datastore-preferences:1.0.0"

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val archCore = "androidx.arch.core:core-testing:2.1.0"
            const val rules = "androidx.test:rules:$version"
            const val junit = "androidx.test.ext:junit-ktx:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
            const val runner = "androidx.test:runner:1.4.0"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
        const val truth = "com.google.truth:truth:1.1.3"
    }

    object Dagger {
        private const val version = "2.48.1"
        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Hilt {
        const val version = "2.51.1"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object Coroutines {
        private const val version = "1.7.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val okHttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Room {
        private const val version = "2.6.1"
        const val common = "androidx.room:room-common:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object Mockito {
        const val core = "org.mockito:mockito-core:2.28.2"
    }

    object Compose {
        const val version = "1.6.5"
        const val compilerVersion = "1.5.11"
        const val lifecycle_version = "2.7.0"

        const val ui = "androidx.compose.ui:ui:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val material = "androidx.compose.material:material:$version"
        const val animation = "androidx.compose.animation:animation:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val viewBinding = "androidx.compose.ui:ui-viewbinding:$version"
        const val util = "androidx.compose.ui:ui-util:$version"
        const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version"
    }

    object Accompanist {
        private const val version = "0.17.0"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val insets_ui = "com.google.accompanist:accompanist-insets-ui:$version"
        const val swipe_refresh = "com.google.accompanist:accompanist-swiperefresh:$version"
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:$version"
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:$version"
    }

    object Coil {
        private const val version = "1.3.2"
        const val compose = "io.coil-kt:coil-compose:$version"
    }
}
