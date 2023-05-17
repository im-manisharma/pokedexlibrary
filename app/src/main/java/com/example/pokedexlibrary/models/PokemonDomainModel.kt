package com.example.pokedexlibrary.models

data class PokemonDomainModel(
    var id: Int? = 0,
    var name: String? = "",
    var imageUrl: String? = "",
    var types: List<String> = listOf(),
    var genders: String = ""
)