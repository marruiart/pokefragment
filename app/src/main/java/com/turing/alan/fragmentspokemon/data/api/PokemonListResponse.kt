package com.turing.alan.fragmentspokemon.data.api

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListItem>
)

data class PokemonListItem(
    val name: String
)

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: PokemonSpritesResponse,
    val types: List<PokemonTypeResponse>
)

data class PokemonSpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class PokemonTypeResponse(
    val type: PokemonNameTypeResponse
)

data class PokemonNameTypeResponse(
    val name: String
)