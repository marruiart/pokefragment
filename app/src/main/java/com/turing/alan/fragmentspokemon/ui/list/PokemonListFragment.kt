package com.turing.alan.fragmentspokemon.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.turing.alan.fragmentspokemon.data.model.Pokemon
import com.turing.alan.fragmentspokemon.databinding.FragmentPokemonListBinding
import com.turing.alan.fragmentspokemon.ui.adapter.PokemonAdapter
import com.turing.alan.fragmentspokemon.ui.list.PokemonListViewModel

class PokemonListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loading.visibility = View.VISIBLE
        var adapter = PokemonAdapter(::onShowDetail)
        binding.pokemonList.adapter = adapter

        // Subscribe to observable from viewModel
        val observer = Observer<List<Pokemon>> {
            // update adapter
            Log.d("LISTA", it.toString())
            adapter.submitList(it)
            binding.loading.visibility = View.GONE
        }

        viewModel.pokemons.observe(viewLifecycleOwner, observer)
    }

    private fun onShowDetail(pokemon: Pokemon, view: View) {
        val action =
            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon.name)
        view.findNavController().navigate(action)
    }
}