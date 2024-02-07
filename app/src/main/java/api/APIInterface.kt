package com.example.retrofitapp.api

import com.example.apipractice.ListaCartas
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface {

    @GET("cards")
    suspend fun getCharacters(): Response<ListaCartas>

    companion object {
        val BASE_URL = "https://api.magicthegathering.io/v1/"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }

}