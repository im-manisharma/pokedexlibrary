package com.example.pokedexlibrary.presentation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pokedexlibrary.R
import com.example.pokedexlibrary.databinding.ActivityPokemonListBinding
import com.example.pokedexlibrary.presentation.adapters.pokemon_list.PokemonListAdapter
import com.example.pokedexlibrary.presentation.uistates.PokemonListUiState
import com.example.pokedexlibrary.presentation.viewmodel.PokemonListViewModel
import com.example.pokedexlibrary.utils.GridSpacingItemDecoration
import com.example.pokedexlibrary.utils.extentions.doGone
import com.example.pokedexlibrary.utils.extentions.doVisible
import com.example.pokedexlibrary.utils.extentions.showToastMsg
import com.example.pokedexlibrary.utils.extentions.toPx
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PokemonListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonListBinding
    private lateinit var pokemonListAdapter: PokemonListAdapter

    private val mViewModel: PokemonListViewModel by lazy {
        getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_list)
        binding.lifecycleOwner = this

        initAdapter()
        initObserver()
        mViewModel.getPokemonList()
    }

    private fun initAdapter() {
        pokemonListAdapter = PokemonListAdapter()
        binding.rvPokemonList.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                16.toPx().toInt(),
                false
            )
        )
        binding.rvPokemonList.adapter = pokemonListAdapter
    }

    private fun initObserver() {
        mViewModel.uiState.observe(this) {
            when (it) {
                is PokemonListUiState.Loading -> {
                    Log.e("MainActivity", "loading")
                    binding.progress.progressBar.doVisible()
                }

                is PokemonListUiState.Success -> {
                    Log.e("MainActivity", "success : ${it.pokemonList?.size}")
                    it.pokemonList?.let { list ->
                        pokemonListAdapter.submitList(list)
                    }
                    binding.progress.progressBar.doGone()
                }

                is PokemonListUiState.Error -> {
                    Log.e("MainActivity", "error : ${it.message}")
                    showToastMsg(it.message)
                    binding.progress.progressBar.doGone()
                }
            }
        }
    }
}