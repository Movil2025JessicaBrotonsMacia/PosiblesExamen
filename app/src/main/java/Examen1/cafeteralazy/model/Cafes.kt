package net.iessochoa.jessicabrotons.cafeteralazy.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Cafes(
    val nombre: String,   // AHORA ES STRING DIRECTO
    val imagen: Int,
    var tipo: String = "Normal"
){
    var tipoState by mutableStateOf(tipo)
}

