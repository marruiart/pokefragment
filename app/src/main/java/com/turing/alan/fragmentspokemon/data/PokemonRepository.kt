package com.turing.alan.fragmentspokemon.data

import com.turing.alan.fragmentspokemon.R
import com.turing.alan.fragmentspokemon.models.Pokemon
import com.turing.alan.fragmentspokemon.models.Types

class PokemonRepository private constructor() {

    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {
            if (_INSTANCE == null) {
                _INSTANCE = PokemonRepository()
            }
            return _INSTANCE!!
        }
    }

    private var _pokemons = mutableListOf<Pokemon>()
    val pokemons: List<Pokemon>
        get() = _pokemons

    init {
        this._pokemons.add(
            Pokemon(
                "Pikachu",
                "Cuando se enfada, este Pokémon descarga la energía que almacena en el interior de las bolsas de las mejillas",
                Types.ELECTRIC,
                null,
                R.drawable.pikachu
            )
        )
        this._pokemons.add(
            Pokemon(
                "Bulbasaur",
                "Este Pokémon nace con una semilla en el lomo, que brota con el paso del tiempo.",
                Types.GRASS,
                Types.POISON,
                R.drawable.bulbasaur
            )
        )
        this._pokemons.add(
            Pokemon(
                "Squirtle",
                "Cuando retrae su largo cuello en el caparazón, dispara agua a una presión increíble",
                Types.WATER,
                null,
                R.drawable.squirtle
            )
        )
        this._pokemons.add(
            Pokemon(
                "Charmander",
                "Prefiere las cosas calientes. Dicen que cuando llueve le sale vapor de la punta de la cola.",
                Types.FIRE,
                null,
                R.drawable.charmander
            )
        )
    }

    fun add(pokemon: Pokemon) {
        _pokemons.add(pokemon)
    }
}