package com.turing.alan.fragmentspokemon.data.api


data class PokemonApiModel(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val frontDefault: String?,
    val primType: String,
    val secType: String?
)

data class PokemonListApiModel(
    val list: List<PokemonApiModel>
)