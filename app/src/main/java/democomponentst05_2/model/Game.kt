package net.iessochoa.sergiocontreras.democomponentst05_2.model

import androidx.annotation.DrawableRes

/**
 * Data class que representa un videojuego.
 *
 * @param title El nombre del juego.
 * @param shortDescription Una descripción corta.
 * @param imageRes El ID del recurso drawable (p.ej., R.drawable.elden_ring).
 */
data class Game(
    val title: String,
    val shortDescription: String,
    @DrawableRes val imageRes: Int // Le dice al IDE que ese Int debe ser un ID de recurso drawable, cualquier otro Int error

    //Este no es su sitio hay que moverlo al package data.
)