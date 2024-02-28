package com.example.apipractice.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharactersDAO{
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): MutableList<CharacterResult>
    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    suspend fun getCharacterById(id:Int): MutableList<CharacterResult>
    @Insert
    suspend fun addCharacter(character: CharacterResult)
    @Delete
    suspend fun deleteCharacter(character: CharacterResult)
}