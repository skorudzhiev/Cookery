package com.skorudzhiev.cookery

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.cookery.CookeryActivity
import com.skorudzhiev.cookery.ui.CookeryApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : CookeryActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CookeryApp()
        }
    }
}
