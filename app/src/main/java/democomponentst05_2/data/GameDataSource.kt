package net.iessochoa.sergiocontreras.democomponentst05_2.data

import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.democomponentst05_2.model.Game

/**
 * Simula una base de datos de juegos.
 */
fun getGameList(): List<Game> {
    return listOf(

        Game(
            "Elden Ring",
            "GOTY 2022, mundo abierto de FromSoftware.",
            R.drawable.elden_ring_v1 // Reemplazar en CWM
        ),
        Game(
            "Baldur's Gate 3",
            "GOTY 2023, RPG clásico por Larian.",
            R.drawable.baldurs_gate_v1 // Reemplazar en CWM
        ),
        Game(
            "Cyberpunk 2077",
            "Rol futurista en Night City.",
            R.drawable.cyberpunk_2077_v1 // Reemplazar en CWM
        ),
        Game(
            "The Witcher 3",
            "Aventuras de Geralt de Rivia.",
            R.drawable.witcher3_v1 // Reemplazar en CWM
        ),
        Game(
            "Red Dead Redemption 2",
            "El oeste salvaje de Rockstar.",
            R.drawable.red_dead_redemption_2_v1 // Reemplazar en CWM
        )
    )
}