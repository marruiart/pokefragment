package com.turing.alan.fragmentspokemon.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.turing.alan.fragmentspokemon.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private  val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokeName.text = args.pokemon.name
        binding.pokeDesription.text = args.pokemon.description
        binding.pokeImg.setImageResource(args.pokemon.image)
        binding.pokeMainType.text = args.pokemon.mainType.toString()
        if (args.pokemon.secType != null) {
            binding.pokeSecType.text = args.pokemon.secType.toString()
        } else {
            binding.pokeSecType.visibility = View.GONE
        }
    }
}