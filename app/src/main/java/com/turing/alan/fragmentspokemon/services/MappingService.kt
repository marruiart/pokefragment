package com.turing.alan.fragmentspokemon.services

import com.turing.alan.fragmentspokemon.data.api.PokemonApiModel
import com.turing.alan.fragmentspokemon.data.api.PokemonDetailResponse
import com.turing.alan.fragmentspokemon.data.model.Pokemon

class MappingService {

    companion object {
        fun mapPokemon(p: PokemonApiModel): Pokemon =
            Pokemon(
                p.id,
                p.name,
                p.weight,
                p.height,
                p.frontDefault,
                p.primType,
                p.secType
            )

        fun mapPokemonApiModel(pokemonResponse: PokemonDetailResponse): PokemonApiModel =
            PokemonApiModel(
                pokemonResponse.id,
                pokemonResponse.name,
                pokemonResponse.weight,
                pokemonResponse.height,
                pokemonResponse.sprites.frontDefault,
                pokemonResponse.types[0].type.name,
                if (pokemonResponse.types.size == 2) pokemonResponse.types[1].type.name else null
            )
    }

}