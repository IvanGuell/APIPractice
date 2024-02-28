package com.example.apipractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "CharacterEntity")
data class CharacterResult(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)