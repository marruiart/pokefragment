package com.turing.alan.fragmentspokemon.ui.list.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.fragmentspokemon.data.api.PokemonApiModel
import com.turing.alan.fragmentspokemon.data.api.PokemonListApiModel
import com.turing.alan.fragmentspokemon.data.api.PokemonRepository
import com.turing.alan.fragmentspokemon.data.model.Pokemon
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val repository = PokemonRepository.getInstance()

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>>
        get() = _pokemons

    private fun mapPokemon(p: PokemonApiModel): Pokemon =
        Pokemon(
            p.id,
            p.name,
            p.weight,
            p.height,
            p.frontDefault,
            p.primType,
            p.secType
        )

    private val observer = Observer<PokemonListApiModel> {
        _pokemons.value = it.list.map {
            mapPokemon(it)
        }
    }

    init {
        fetchAll()
    }

    fun fetchOne(id: Int) {
        viewModelScope.launch {
            // scope to execute suspendable functions
            repository.fetchOne(id)
        }
    }

    fun fetchAll() {
        viewModelScope.launch {
            // scope to execute suspendable functions
            repository.pokemons.observeForever(observer)
            viewModelScope.launch {
                repository.fetchList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository
            .pokemons
            .removeObserver(observer)
    }

}