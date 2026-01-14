package net.iessochoa.sergiocontreras.thedogwalker.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerViewModel
import net.iessochoa.sergiocontreras.thedogwalker.ui.components.DogCard

@Composable
fun DogListScreen(
    viewModel: DogWalkerViewModel,
    //dogs: List<Dog>,
    onDogClick: (Dog) -> Unit, //AQUI (DOG) Y NO (INT)
    modifier: Modifier = Modifier
) {

    //Nos falta observar el estado
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(uiState.dogs) { dog ->
            DogCard(
                dog = dog,
                onClick = {
                    //Avisar al ViewModel de que perro ha sido seleccionado
                    viewModel.onDogSelected(dog)

                    //Ejecutar la lambda de navegación para ir al detalle del perro
                    onDogClick(dog)
                }
            )
        }
    }
}