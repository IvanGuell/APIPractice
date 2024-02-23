package com.example.apipractice.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(route: String, icon: ImageVector, label: String) {
    object Home : BottomNavigationScreens(Routes.MainScreen.route, Icons.Filled.Home, "Home")
    object Favorite :
        BottomNavigationScreens(Routes.FavoritesScreen.route, Icons.Filled.Favorite, "Favorite")

    object Settings :
        BottomNavigationScreens(Routes.SettingsScreen.route, Icons.Filled.Settings, "Settings")
}


