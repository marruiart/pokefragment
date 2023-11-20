package com.turing.alan.fragmentspokemon.services

import com.turing.alan.fragmentspokemon.data.api.PokemonApiModel
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
    }

}