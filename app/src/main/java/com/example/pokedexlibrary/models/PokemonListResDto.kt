package com.example.pokedexlibrary.models

import com.google.gson.annotations.SerializedName

data class PokemonListResDto(
    @SerializedName("results")
    var pokemonList: List<PokemonDto>? = listOf()
)
