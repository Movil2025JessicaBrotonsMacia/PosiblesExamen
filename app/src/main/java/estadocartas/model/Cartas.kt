package net.iessochoa.jessicabrotons.estadocartas.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Cartas(
    @StringRes val stringResourceId: Int,
    @DrawableRes val drawableResourceId: Int
)
