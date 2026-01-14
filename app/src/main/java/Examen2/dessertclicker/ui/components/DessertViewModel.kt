package com.example.dessertclicker.ui.components

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.determineDessertToShow
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow() //Solo lectura

    fun onDessertClicked(desserts: List<Dessert>){
        _uiState.update { currentState -> //Cogemos el valor actual del postre con currentState

            val sold = currentState.dessertsSold + 1 //Cada vez que hagamos click, añadimos 1 a los postres vendidos
            val revenue = currentState.revenue + desserts[currentState.currentDessertIndex].price //Sumamos a los ingresos el precio del postre
            val nextDessert = determineDessertToShow(desserts, sold) //Buscar el índice del próximo postre (o el mismo)
            val nextIndex = desserts.indexOf(nextDessert) //Obtener el índice (si el postre se va a mostrar 5 clicks sigo mostrando ese mismo próximo postre)
            val dessertImage = desserts[nextIndex].imageId //Obtener la imagen del próximo postre

                currentState.copy(
                dessertsSold = sold,
                revenue = revenue,
                currentDessertIndex = nextIndex,
                    dessertImage = dessertImage
            )
        }
    }

}