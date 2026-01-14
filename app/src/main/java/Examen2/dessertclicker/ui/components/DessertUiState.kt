package com.example.dessertclicker.ui.components

import net.iessochoa.jessicabrotons.posibleexamen.R


//Crear clase de datos con todos los datos necesarios para la IU
data class DessertUiState(
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertIndex: Int = 0,
    val dessertImage: Int = R.drawable.cupcake
)