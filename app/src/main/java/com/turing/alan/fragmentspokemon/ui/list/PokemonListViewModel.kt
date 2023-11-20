package com.turing.alan.fragmentspokemon.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.fragmentspokemon.data.api.PokemonApiModel
import com.turing.alan.fragmentspokemon.data.api.PokemonListApiModel
import com.turing.alan.fragmentspokemon.data.api.PokemonRepository
import com.turing.alan.fragmentspokemon.data.model.Pokemon
import com.turing.alan.fragmentspokemon.services.MappingService
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val repository = PokemonRepository.getInstance()

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>>
        get() = _pokemons

    private val observer = Observer<PokemonListApiModel> {
        _pokemons.value = it.list.map {
            MappingService.mapPokemon(it)
        }
    }

    init {
        fetchAll()
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