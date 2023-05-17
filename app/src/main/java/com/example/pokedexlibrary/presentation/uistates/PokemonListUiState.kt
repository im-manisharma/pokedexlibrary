package com.example.pokedexlibrary.presentation.uistates

import com.example.pokedexlibrary.models.PokemonDomainModel
import com.example.pokedexlibrary.models.PokemonDto

// Represents different states for the AllAnime screen
sealed class PokemonListUiState {
    object Loading: PokemonListUiState()
    data class Success(val pokemonList: List<PokemonDto>?): PokemonListUiState()
    data class Error(val message: String?): PokemonListUiState()
}
