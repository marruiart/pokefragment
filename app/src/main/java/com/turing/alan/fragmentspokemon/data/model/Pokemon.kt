package com.turing.alan.fragmentspokemon.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val image: String?,
    val primType: String,
    val secType: String?
) : Parcelable
