package com.example.retrofitapp.api

import androidx.lifecycle.MutableLiveData

class Repository {

    val apiInterface = APIInterface.create()

    suspend fun getAllCharacters(pagina: Int) = apiInterface.getCharacters(pagina = pagina)
    suspend fun getCharacter(characterId: Int) = apiInterface.getCharacterDetail(characterId)
} 
/*
            Result {
        return apiInterface.getCharacterDetail(characterId).body() ?: throw NoSuchElementException("Character not found")
    }}*/
