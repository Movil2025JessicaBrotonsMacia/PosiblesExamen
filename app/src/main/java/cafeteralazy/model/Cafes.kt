package net.iessochoa.jessicabrotons.cafeteralazy.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Cafes(
    @StringRes val nombre: Int,
    @DrawableRes val imagen: Int,
    var tipo: String = "Normal"
)
