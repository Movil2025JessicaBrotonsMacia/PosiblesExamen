package net.iessochoa.jessicabrotons.cafeteralazy.data


import net.iessochoa.jessicabrotons.cafeteralazy.model.Cafes
import net.iessochoa.jessicabrotons.posibleexamen.R

class ListaCafes {
    fun loadListaCafes(): List<Cafes> {
        return listOf<Cafes>(
            Cafes("Solo", R.drawable.solo, ""),
            Cafes("Cortado", R.drawable.cortado, ""),
            Cafes("Con Leche", R.drawable.leche, ""),
            Cafes("Bombón", R.drawable.bombon, "")
        )
    }
}