package com.example.apipractice.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(CharacterResult::class), version = 1)
abstract class CharacterDatabase:RoomDatabase(){
    abstract fun characterDao():CharactersDAO
}