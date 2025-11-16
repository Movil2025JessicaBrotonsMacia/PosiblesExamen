package net.iessochoa.jessicabrotons.estadocartas.data

import net.iessochoa.jessicabrotons.estadocartas.model.Cartas
import net.iessochoa.jessicabrotons.posibleexamen.R

class Datasource {

    fun getCartas(): List<Cartas>{
        return listOf<Cartas>(
            Cartas(R.string.charmander, R.drawable.charmander),
            Cartas(R.string.squirtle, R.drawable.squirtle),
            Cartas(R.string.bulbasur, R.drawable.bulbasur),
            Cartas(R.string.pikachu, R.drawable.pikachu)
        )
    }
}