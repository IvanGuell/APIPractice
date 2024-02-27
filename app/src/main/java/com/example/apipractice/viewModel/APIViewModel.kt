package com.example.apipractice.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apipractice.model.Data
import com.example.apipractice.model.Info
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
    var characterId = 0
    var pagina = 1
    private val _searchText = MutableLiveData<String>()
    val searchText = _searchText

    private val _character = MutableLiveData<Result>()
    val character = _character

    private val _charactersAPI = MutableLiveData<Data>()
    val charactersAPI = _charactersAPI
    private val _show = MutableLiveData<Boolean>()
    val show = _show


    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters(pagina)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _charactersAPI.value = _characters.value
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
            val response = repository.getCharacter(characterId)
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

    fun set_CharcterID(id: Int){
        this.characterId = id
    }

    fun onSearchTextChange(text: String){
        _searchText.value = text
        var charactersFiltered = Data(Info(1, "next", "prev", 42),
            _charactersAPI.value!!.results.filter { it.name.lowercase().contains(text.lowercase()) })
        _characters.value = charactersFiltered
        if (text.isEmpty()) _characters.value = _charactersAPI.value


    }

    fun changeShow() {
        _show.value = ! _show.value
    }
}




