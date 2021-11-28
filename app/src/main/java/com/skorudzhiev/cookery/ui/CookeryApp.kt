package com.skorudzhiev.cookery.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.cookery.common.compose.theme.CookeryTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.skorudzhiev.cookery.ui.navigation.AppNavigation
import com.skorudzhiev.cookery.ui.navigation.CookeryBottomNavigationBar
import com.skorudzhiev.cookery.ui.navigation.HomeSections

@Preview
@Composable
fun CookeryApp() {
    ProvideWindowInsets {
        CookeryTheme {
            val tabs = remember { HomeSections.values() }
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { CookeryBottomNavigationBar(navController = navController, tabs = tabs) }
            ) { innerPaddingModifier ->
                AppNavigation(
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}
