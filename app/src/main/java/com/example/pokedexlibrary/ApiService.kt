package com.example.pokedexlibrary

import com.example.pokedexlibrary.models.PokemonListResDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int = 100,
    ): PokemonListResDto
}
