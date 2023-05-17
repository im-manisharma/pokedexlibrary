package com.example.pokedexlibrary.models

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)
