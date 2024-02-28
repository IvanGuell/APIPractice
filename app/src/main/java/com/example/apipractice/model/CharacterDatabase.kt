package com.example.apilistapp.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.example.apipractice.model.CharacterResult
import com.example.apipractice.model.CharactersDAO

@Database(entities = arrayOf(CharacterResult::class), version = 1)
abstract class CharacterDatabase:RoomDatabase(){
    abstract fun characterDao():CharactersDAO
}