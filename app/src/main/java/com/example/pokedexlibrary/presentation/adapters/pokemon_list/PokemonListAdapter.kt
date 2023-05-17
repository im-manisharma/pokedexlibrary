package com.example.pokedexlibrary.presentation.adapters.pokemon_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pokedexlibrary.databinding.ListItemPokemonBinding
import com.example.pokedexlibrary.models.PokemonDto

class PokemonListAdapter: ListAdapter<PokemonDto, PokemonListViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<PokemonDto>() {
        override fun areItemsTheSame(oldItem: PokemonDto, newItem: PokemonDto) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: PokemonDto, newItem: PokemonDto) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            ListItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindData(data)
        holder.itemView.setOnClickListener {

        }
    }
}