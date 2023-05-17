package com.example.pokedexlibrary.koin

import com.example.pokedexlibrary.presentation.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myViewModel = module {
    viewModel { PokemonListViewModel() }
}