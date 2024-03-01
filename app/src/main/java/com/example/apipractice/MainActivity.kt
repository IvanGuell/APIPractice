package com.example.apipractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavController
import com.example.apipractice.ui.theme.APIPracticeTheme
import com.example.apipractice.viewModel.APIViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apipractice.navigation.BottomNavigationScreens
import com.example.apipractice.navigation.Routes
import com.example.apipractice.view.DetailScreen
import com.example.apipractice.view.FavoritesScreen
import com.example.apipractice.view.MainScreen
import com.example.apipractice.view.MyRecyclerView
import com.example.apipractice.view.paginationButtons


class MainActivity : ComponentActivity() {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Favorite
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        val myViewModel by viewModels<APIViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            APIPracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()

                    Scaffold(
                        bottomBar = { MyBottomAppBar(navigationController, bottomNavigationItems) },
                        topBar = { MyTopAppBar(navigationController, myViewModel)}
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {

                            NavHost(
                                navController = navigationController,
                                startDestination = Routes.MainScreen.route
                            ) {
                                composable(Routes.MainScreen.route) {
                                    MainScreen(navController = navigationController, myViewModel)
                                }
                                composable(Routes.DetailScreen.route) {
                                    DetailScreen(navController = navigationController, myViewModel)
                                }
                                composable(Routes.FavoritesScreen.route) {
                                    FavoritesScreen(navController = navigationController, myViewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun MyBottomAppBar(navController: NavController, bottomNavigationItems: List<BottomNavigationScreens>) {

    BottomNavigation(backgroundColor = Color.Red) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                    )
                },

                label = { androidx.compose.material.Text("") },
                selected = currentRoute == item.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = false,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController, apiViewModel: APIViewModel) {
    val showSearchBar: Boolean by apiViewModel.show.observeAsState(false)



    TopAppBar(
        title = { Text(text = "Ricky Morty te saludan") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),

        actions = {

            IconButton(onClick = { apiViewModel.changeShow() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            if (showSearchBar){
                MySearchBar(apiViewModel)

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar (apiViewModel: APIViewModel) {
    val searchText by apiViewModel.searchText.observeAsState("")
    SearchBar(
        query = searchText,
        onQueryChange = { apiViewModel.onSearchTextChange(it) },
        onSearch = { apiViewModel.onSearchTextChange(it) },
        active = true,
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")},
        placeholder = { Text("Introduce para buscar") },
        onActiveChange = {},
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .clip(RectangleShape)) {

    }
}

