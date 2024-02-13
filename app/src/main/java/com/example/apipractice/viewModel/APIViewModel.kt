package com.example.apipractice.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apipractice.model.Data
import com.example.retrofitapp.api.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.apipractice.model.Result


class APIViewModel: ViewModel() {

    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _characters = MutableLiveData<Data>()
    val characters = _characters
    private val _characterId = MutableLiveData<Int>()
    val characterId = _characterId

    private val _character = MutableLiveData<Result>()
    val character = _character


    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getCharactersInt(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacter(_characterId)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _character.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }
}

