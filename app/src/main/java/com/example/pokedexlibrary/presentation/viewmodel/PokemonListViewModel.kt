package com.example.pokedexlibrary.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexlibrary.ApiService
import com.example.pokedexlibrary.presentation.uistates.PokemonListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class PokemonListViewModel : ViewModel() {
    private val apiService: ApiService by inject(clazz = ApiService::class.java)
    var limitCount: Int = 30

    private val _uiState = MutableLiveData<PokemonListUiState>(PokemonListUiState.Loading)
    val uiState: LiveData<PokemonListUiState> = _uiState

    fun getPokemonList() {
        _uiState.value = PokemonListUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getPokemon(limitCount)
                _uiState.postValue(PokemonListUiState.Success(response.pokemonList))
            }catch (e: Exception){
                _uiState.postValue(PokemonListUiState.Error(e.message))
            }
        }
    }
}