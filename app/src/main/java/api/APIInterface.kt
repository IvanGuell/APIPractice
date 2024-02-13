package com.example.retrofitapp.api

import androidx.lifecycle.MutableLiveData
import com.example.apipractice.model.Data
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.apipractice.model.Result

interface APIInterface {

    @GET("character")
    suspend fun getCharacters(): Response<Data>
    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") charId: MutableLiveData<Int>): Response<Result>

    companion object {
        val BASE_URL = "https://rickandmortyapi.com/api/"
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