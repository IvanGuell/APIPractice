package com.example.apipractice.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apipractice.navigation.Routes
import com.example.apipractice.model.Data
import com.example.apipractice.model.Info
import com.example.apipractice.model.CharacterResult
import com.example.apipractice.viewModel.APIViewModel


@Composable
fun MainScreen(navController: NavController, apiViweModel: APIViewModel) {


    MyRecyclerView(apiViweModel, navController)
    PaginationButtons(apiViweModel)

}

@Composable
fun MyRecyclerView(apiViweModel: APIViewModel, navController: NavController) {
    val showLoading: Boolean by apiViweModel.loading.observeAsState(true)
    val characters: Data by apiViweModel.characters.observeAsState(
        Data(
            info = Info(0, "", "", 42),
            emptyList()
        )
    )

    apiViweModel.getCharacters()
    if (showLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    } else {
        LazyColumn {
            items(characters.results) { character ->
                CharacterItem(character, navController, apiViweModel)
            }
        }
    }
}

@Composable
fun PaginationButtons(apiViewModel: APIViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 600.dp)
    ) {

        Button(
            onClick = {
                apiViewModel.pagina--
                apiViewModel.getCharacters()

            },
            enabled = apiViewModel.pagina != null,
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(1.dp),
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF468499),
                contentColor = MaterialTheme.colorScheme.primary
            )

        ) {
            Text(
                text = "Anterior",
                color = Color.Black,
                fontWeight = FontWeight.Bold

            )

        }
        Button(
            onClick = {
                apiViewModel.pagina++
                apiViewModel.getCharacters()
            },
            enabled = apiViewModel.pagina != null,
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(1.dp),
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF468499),
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Siguiente",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(
    character: CharacterResult,
    navController: NavController,
    apiViweModel: APIViewModel
) {
    println(character.image)

    Card(

        border = BorderStroke(2.dp, Color(0xFFb0e0e6)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(Routes.DetailScreen.route)
                apiViweModel.set_Id(character.id)


            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFb0e0e6)),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFb0e0e6))
            ) {
                GlideImage(
                    model = character.image,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



