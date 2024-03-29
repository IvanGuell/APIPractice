package com.example.apipractice.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apipractice.model.CharacterResult
import com.example.apipractice.navigation.Routes
import com.example.apipractice.viewModel.APIViewModel


@Composable
fun FavoritesScreen(navController: NavController, apiViewModel: APIViewModel) {

    val favorites: MutableList<CharacterResult> by apiViewModel.favorites.observeAsState(
        mutableListOf()
    )
    apiViewModel.getFavorites()
    if (favorites.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(favorites) {
                Favorites(
                    character = it,
                    apiViewModel = apiViewModel,
                    navController = navController
                )
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Favorites(
    character: CharacterResult,
    apiViewModel: APIViewModel,
    navController: NavController
) {
    Card(
        border = BorderStroke(2.dp, Color(0xBEB7DCEF)),

        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(Routes.DetailScreen.route)
                apiViewModel.set_Id(character.id)
            }

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    GlideImage(
                        model = character.image,
                        contentDescription = "Character",
                        contentScale = ContentScale.Crop,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFb0e0e6)),

                        ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            text = character.name,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = Color.Black
                        )
                    }
                }

            }
        }
    }
}