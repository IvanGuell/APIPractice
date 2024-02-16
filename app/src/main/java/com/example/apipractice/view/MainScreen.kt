package com.example.apipractice.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apipractice.Routes
import com.example.apipractice.model.Data
import com.example.apipractice.model.Info
import com.example.apipractice.model.Result
import com.example.apipractice.viewModel.APIViewModel


@Composable
fun MainScreen(navController: NavController, apiViweModel: APIViewModel){
    MyRecyclerView(apiViweModel, navController)

    paginationButtons(navController, apiViweModel)

}

@Composable
fun MyRecyclerView(apiViweModel: APIViewModel, navController: NavController) {
    val showLoading: Boolean by apiViweModel.loading.observeAsState(true)
    val characters: Data by apiViweModel.characters.observeAsState(Data(info = Info(0, "", "", 42 ), emptyList()))

    apiViweModel.getCharacters()
    if(showLoading){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
    else{
        LazyColumn() {
            items(characters.results) { character ->
                CharacterItem(character, navController, apiViweModel)
            }
        }
    }
}
@Composable
fun paginationButtons(navController: NavController, apiViewModel: APIViewModel){

Row {


    Button(
        onClick = {
            apiViewModel.pagina++
            apiViewModel.getCharacters()
        },
        enabled = apiViewModel.pagina != null,
        modifier = Modifier
            .weight(1f)
            .padding(end = 10.dp, top = 50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = "Siguiente",
            color = Color.White
        )
    }
    Button(
        onClick = {
            apiViewModel.pagina--
            apiViewModel.getCharacters()

        },
        enabled = apiViewModel.pagina != null,
        modifier = Modifier
            .weight(1f)
            .padding(end = 50.dp, top = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = "Anterior",
            color = Color.Black
        )

    }
}
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Result, navController: NavController, apiViweModel: APIViewModel) {
    println(character.image)

    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(Routes.DetailScreen.createRoute(character.id.toString()))
                apiViweModel.set_CharcterID(apiViweModel.characterId)
            }
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            GlideImage(
                model = character.image,
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize()
            )
        }
    }
}

