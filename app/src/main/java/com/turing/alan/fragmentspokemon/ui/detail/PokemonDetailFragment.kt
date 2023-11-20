package com.turing.alan.fragmentspokemon.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.turing.alan.fragmentspokemon.data.model.Pokemon
import com.turing.alan.fragmentspokemon.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {
    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailBinding
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    // Subscribe to observable from viewModel
    val observer = Observer<Pokemon> {
        binding.pokeName.text = it.name
        binding.pokeImg.load(it.image)
        binding.pokeMainType.text = it.primType
        if (it.secType != null) {
            binding.pokeSecType.text = it.secType
        } else {
            binding.pokeSecType.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchOne(args.pokeName)
        viewModel.pokemon.observe(viewLifecycleOwner, observer)
    }
}