package com.turing.alan.fragmentspokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val description: String,
    val mainType: Enum<Types>,
    val secType: Enum<Types>?,
    val image: Int
) : Parcelable