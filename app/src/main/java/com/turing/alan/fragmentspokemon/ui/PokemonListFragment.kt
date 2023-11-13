package com.turing.alan.fragmentspokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.turing.alan.fragmentspokemon.data.PokemonRepository
import com.turing.alan.fragmentspokemon.databinding.FragmentPokemonListBinding
import com.turing.alan.fragmentspokemon.models.Pokemon
import com.turing.alan.fragmentspokemon.ui.adapter.PokemonAdapter

class PokemonListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding
            .inflate(inflater, container, false)
        binding.pokemonList.adapter =
            PokemonAdapter(PokemonRepository.getInstance().pokemons, ::onShowDetail)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Recycler view y enganchar el adaptador
        val recyclerView = binding.pokemonList
        val adapter = PokemonAdapter(PokemonRepository.getInstance().pokemons, ::onShowDetail)
    }

    private fun onShowDetail(pokemon: Pokemon, view: View) {
        val action =
            PokemonListFragmentDirections
                .actionPokemonListFragmentToPokemonDetailFragment(pokemon)
        view.findNavController().navigate(action)
    }
}