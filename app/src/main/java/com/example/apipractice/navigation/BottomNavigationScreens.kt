package com.example.apipractice.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(val route: String, val icon:ImageVector, val label: String) {
    object Home:BottomNavigationScreens(Routes.MainScreen.route, Icons.Filled.Home, "Home")
    object Favorite:BottomNavigationScreens(Routes.FavoritesScreen.route, Icons.Filled.FavoriteBorder, "Favorite")
}


