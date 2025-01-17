package com.turing.alan.fragmentspokemon.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.turing.alan.fragmentspokemon.services.MappingService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("api/v2/pokemon/{id}")
    suspend fun fetchPokemon(@Path("id") id: Int): PokemonDetailResponse

    @GET("api/v2/pokemon/{name}")
    suspend fun fetchPokemon(@Path("name") name: String): PokemonDetailResponse

    @GET("api/v2/pokemon")
    suspend fun fetchPokemonsList(): PokemonListResponse
}

class PokemonRepository private constructor(private val api: PokemonApi) {

    private var _pokemons = MutableLiveData<PokemonListApiModel>()
    val pokemons: LiveData<PokemonListApiModel>
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

    suspend fun fetchOne(id: Int): PokemonApiModel = MappingService.mapPokemonApiModel(api.fetchPokemon(id))

    suspend fun fetchOne(name: String): PokemonApiModel = MappingService.mapPokemonApiModel(api.fetchPokemon(name))

    suspend fun fetchList() {
        val pokemonListResponse = api.fetchPokemonsList()
        val pokemonList = pokemonListResponse.results.map {
            fetchOne(it.name)
        }
        val pokemonListApiModel = PokemonListApiModel(pokemonList)
        _pokemons.value = pokemonListApiModel
    }
}