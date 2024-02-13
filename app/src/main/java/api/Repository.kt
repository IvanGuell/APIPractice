package com.example.retrofitapp.api

import androidx.lifecycle.MutableLiveData

class Repository {

    val apiInterface = APIInterface.create()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
    suspend fun getCharacter(characterId: MutableLiveData<Int>) = apiInterface.getCharacterDetail(characterId)
} 
/*
            Result {
        return apiInterface.getCharacterDetail(characterId).body() ?: throw NoSuchElementException("Character not found")
    }}*/
