package com.example.apipractice.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.apipractice.viewModel.APIViewModel
import com.example.apipractice.model.Result

@Composable
fun DetailScreen(navController: NavController, apiViewModel: APIViewModel) {
    val characterId by remember { mutableStateOf(apiViewModel.characterId) }


}
