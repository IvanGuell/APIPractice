package com.example.apipractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.apipractice.ui.theme.APIPracticeTheme
import com.example.apipractice.viewModel.APIViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apipractice.navigation.Routes
import com.example.apipractice.view.DetailScreen
import com.example.apipractice.view.FavoritesScreen
import com.example.apipractice.view.MainScreen
import com.example.apipractice.view.SettingsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myViewModel by viewModels<APIViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            APIPracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
                val navigationController = rememberNavController()

                NavHost(
                    navController = navigationController,
                    startDestination = Routes.MainScreen.route
                ) {
                    composable(Routes.MainScreen.route) { MainScreen(navController = navigationController, myViewModel) }
                    composable(Routes.DetailScreen.route) { DetailScreen(navController = navigationController, myViewModel) }
                    composable(Routes.FavoritesScreen.route) { FavoritesScreen(navController = navigationController, myViewModel) }
                    composable(Routes.SettingsScreen.route) { SettingsScreen(navController = navigationController, myViewModel) }

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

