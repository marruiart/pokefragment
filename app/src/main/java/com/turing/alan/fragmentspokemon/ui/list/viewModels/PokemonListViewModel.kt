package com.turing.alan.fragmentspokemon.ui.list.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.fragmentspokemon.data.api.PokemonApiModel
import com.turing.alan.fragmentspokemon.data.api.PokemonRepository
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val repository = PokemonRepository.getInstance()
    val pokemons: LiveData<PokemonApiModel>
        get() = repository.pokemons

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            // scope to execute suspendable functions
            repository.fetch(1)
        }
    }

}