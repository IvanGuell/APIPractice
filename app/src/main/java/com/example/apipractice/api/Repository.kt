package com.example.retrofitapp.api

import androidx.lifecycle.MutableLiveData
import com.example.apipractice.model.CharacterApplication
import com.example.apipractice.model.CharacterResult

class Repository {

    val apiInterface = APIInterface.create()
    val daoInterface = CharacterApplication.database.characterDao()

    suspend fun getAllCharacters(pagina: Int) = apiInterface.getCharacters(pagina = pagina)
    suspend fun getCharacter(characterId: Int) = apiInterface.getCharacterDetail(characterId)


    suspend fun saveAsFavorite(character:CharacterResult) = daoInterface.addCharacter(character)
    suspend fun deleteFavorite(character:CharacterResult) = daoInterface.deleteCharacter(character)
    suspend fun isFavorite(character:CharacterResult) = daoInterface.getCharacterById(character.id).isNotEmpty()
    suspend fun getFavorites()= daoInterface.getAllCharacters()

} 
/*
            Result {
        return apiInterface.getCharacterDetail(characterId).body() ?: throw NoSuchElementException("Character not found")
    }}*/
