package net.iessochoa.jessicabrotons.cafeteralazy.data


import net.iessochoa.jessicabrotons.cafeteralazy.model.Cafes
import net.iessochoa.jessicabrotons.posibleexamen.R

class ListaCafes {
    fun loadListaCafes(): List<Cafes> {
        return listOf<Cafes>(
            Cafes(R.string.solo, R.drawable.solo),
            Cafes(R.string.cortado, R.drawable.cortado),
            Cafes(R.string.conLeche, R.drawable.leche),
            Cafes(R.string.bombon, R.drawable.bombon)
        )
    }
}