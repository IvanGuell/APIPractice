package com.example.apipractice.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apipractice.R
import com.example.apipractice.viewModel.APIViewModel
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(navController: NavController, apiViewModel: APIViewModel) {
    apiViewModel.getCharacter()
    val character by apiViewModel.character.observeAsState()


    character?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            GlideImage(
                model = it.image,
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Box(
                modifier = Modifier
                    .padding(start = 192.dp, top = 32.dp)
                    .clickable {
                    }
            ) {
                FavButton(apiViewModel)
            }
            Text(
                text = it.name,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Genre: ${it.gender}",
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )

            Text(
                text = "Specie: ${it.species}",
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
        }
    } ?: run {
        Text(
            text = "Detalles no disponibles",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun FavButton(apiViewModel: APIViewModel) {
    val isFavorite by apiViewModel.isFavorite.observeAsState(false)
    val character by apiViewModel.character.observeAsState()

    Icon(
        tint = Color(0xFFE46F92),
        modifier = Modifier
            .graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            }
            .clickable {
                apiViewModel.favController(character, isFavorite)
            },
        imageVector = if (isFavorite == true) {
            Icons.Filled.Favorite
        } else {
            Icons.Default.FavoriteBorder
        },
        contentDescription = null
    )
}