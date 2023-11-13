package com.turing.alan.fragmentspokemon.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.fragmentspokemon.databinding.PokemonItemBinding
import com.turing.alan.fragmentspokemon.models.Pokemon

class PokemonAdapter(
    private val pokemons: List<Pokemon>,
    private val onShowDetail: (p: Pokemon, view: View) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPokemon(p: Pokemon) {
            binding.pokeName.text = p.name
            binding.pokeDesription.text = p.description
            binding.pokeImg.setImageResource(p.image)
            binding.pokeMainType.text = p.mainType.toString()
            if (p.secType != null) {
                binding.pokeSecType.text = p.secType.toString()
            } else {
                binding.pokeSecType.visibility = View.GONE
            }
            binding.card.setOnClickListener{
                onShowDetail(p, binding.root)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bindPokemon(pokemon)
    };


}