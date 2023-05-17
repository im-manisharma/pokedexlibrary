package com.example.pokedexlibrary.presentation.adapters.pokemon_list

import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexlibrary.databinding.ListItemPokemonBinding
import com.example.pokedexlibrary.models.PokemonDto

class PokemonListViewHolder(
    private val binding: ListItemPokemonBinding,
): RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: PokemonDto) {
        binding.data = data
        val paddedValue = (layoutPosition+1).toString().padStart(3, '0')
        binding.imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${paddedValue}.png"
        binding.id = paddedValue
        binding.executePendingBindings()
    }
}