package com.example.apipractice.api

import com.example.apipractice.model.CharacterApplication
import com.example.apipractice.model.CharacterResult
import com.example.retrofitapp.api.APIInterface

class Repository {

    val apiInterface = APIInterface.create()
    val daoInterface = CharacterApplication.database.characterDao()

    suspend fun getAllCharacters(pagina: Int) = apiInterface.getCharacters(pagina = pagina)
    suspend fun getCharacter(characterId: Int) = apiInterface.getCharacterDetail(characterId)


    suspend fun saveAsFavorite(character: CharacterResult) = daoInterface.addCharacter(character)
    suspend fun deleteFavorite(character: CharacterResult) = daoInterface.deleteCharacter(character)
    suspend fun isFavorite(character: CharacterResult) =
        daoInterface.getCharacterById(character.id).isNotEmpty()

    suspend fun getFavorites() = daoInterface.getAllCharacters()

}
