package com.turing.alan.fragmentspokemon.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turing.alan.fragmentspokemon.data.PokemonRepository
import com.turing.alan.fragmentspokemon.models.Pokemon

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _data: MutableLiveData<List<Pokemon>> = MutableLiveData()

    val data: LiveData<List<Pokemon>>
        get() = _data

    init {
        fetchTasks() // assure that _data is never null
    }

    fun fetchTasks() {
        _data.value = repository.pokemons
    }

    fun add(pokemon: Pokemon) {
        repository.add(pokemon)
        fetchTasks()
    }



}