package com.turing.alan.fragmentspokemon.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("api/v2/pokemon/{id}")
    suspend fun fetchPokemon(@Path("id") id: Int): PokemonApiModel
}

class PokemonRepository private constructor(private val api: PokemonApi) {

    private var _pokemons = MutableLiveData<PokemonApiModel>()
    val pokemons: LiveData<PokemonApiModel>
        get() = _pokemons

    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!
        }
    }

    suspend fun fetch(id: Int) {
        val pokemonResponse = api.fetchPokemon(id)
        _pokemons.value = pokemonResponse
        Log.d("pokemon", pokemonResponse.toString())
    }
}