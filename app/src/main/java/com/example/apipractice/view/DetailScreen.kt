package com.example.apipractice.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
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
        apiViewModel.isFavorite(it)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
            ) {
                GlideImage(
                    model = it.image,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Box (modifier = Modifier.offset(330.dp, 25.dp)){

                    FavButton(apiViewModel)
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(200.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                border = BorderStroke(2.dp, Color(0xFF5BE5E9)),
                shape = RoundedCornerShape(8.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF5BE5E9)),
                ) {

                    Text(
                        text = "Name: ${it.name}",
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Gender: ${it.gender}",
                        modifier = Modifier
                            .padding(vertical = 48.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Specie: ${it.species}",
                        modifier = Modifier
                            .padding(vertical = 78.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }

            }

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
        tint = Color(0xFF5BE5E9),
        modifier = Modifier
            .graphicsLayer {
                scaleX = 1.9f
                scaleY = 1.9f
            }
            .offset(-10.dp, -5.dp)
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