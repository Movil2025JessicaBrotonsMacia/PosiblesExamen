package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

import net.iessochoa.jessicabrotons.posibleexamen.R


interface NavigationDestination {
    // TODO: Define las propiedades de la interfaz
    val route: String
    val titleRes: Int
}

// Destino 1: La pantalla principal (Lista de Perros)
object DogListDestination : NavigationDestination {
    // TODO: Implementa las propiedades de la interfaz para la pantalla de lista
    override val route = "dog_list"
    override val titleRes = R.string.app_name_dog
}

// Destino 2: La pantalla secundaria (Detalle del Perro)
object DogDetailDestination : NavigationDestination {
    // TODO: Implementa las propiedades de la interfaz para la pantalla de detalle
    override val route = "dog_detail"
    override val titleRes = R.string.dog_detail
}