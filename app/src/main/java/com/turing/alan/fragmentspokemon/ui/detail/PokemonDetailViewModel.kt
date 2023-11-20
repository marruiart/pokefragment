package com.turing.alan.fragmentspokemon.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.fragmentspokemon.data.api.PokemonRepository
import com.turing.alan.fragmentspokemon.data.model.Pokemon
import com.turing.alan.fragmentspokemon.services.MappingService
import kotlinx.coroutines.launch

class PokemonDetailViewModel: ViewModel() {
    private val repository = PokemonRepository.getInstance()

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    fun fetchOne(name: String) {
        viewModelScope.launch {
            // scope to execute suspendable functions
            _pokemon.value = MappingService.mapPokemon(repository.fetchOne(name))
        }
    }

}