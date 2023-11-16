package com.turing.alan.fragmentspokemon.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.fragmentspokemon.data.model.Pokemon
import com.turing.alan.fragmentspokemon.databinding.PokemonItemBinding

class PokemonAdapter(
    private val onShowDetail: (p: Pokemon, view: View) -> Unit
) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonDiffCallBack()) {

    inner class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPokemon(p: Pokemon) {
            binding.pokeName.text = p.name
            binding.pokeImg.load(p.image)
            binding.pokeMainType.text = p.primType
            if (p.secType != null) {
                binding.pokeSecType.text = p.secType
            } else {
                binding.pokeSecType.visibility = View.GONE
            }
            binding.card.setOnClickListener {
                onShowDetail(p, binding.root)
            }
        }
    }

    private class PokemonDiffCallBack : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bindPokemon(pokemon)
    };


}