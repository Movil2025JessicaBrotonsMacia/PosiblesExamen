package net.iessochoa.jessicabrotons.cartasfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import net.iessochoa.jessicabrotons.cartasfer.ui.components.Card
import net.iessochoa.jessicabrotons.cartasfer.ui.components.DropDown
import net.iessochoa.jessicabrotons.cartasfer.data.ListaCartas
import net.iessochoa.jessicabrotons.cartasfer.ui.components.RatingBar
import net.iessochoa.jessicabrotons.posibleexamen.R


@Composable
fun MostrarEjercicio(){

    //Para el dropdown y el snackbarHost
    var opcionSeleccionada by remember { mutableStateOf(ListaCartas.cartas.first().nombre) }

    //Para el ratingBar
    var rating by remember { mutableStateOf(0) }

    //Para el FAB
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    //Pasarselo a Card depende de la opción seleccionada
    val carta = ListaCartas.cartas.first { it.nombre == opcionSeleccionada }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = mostrarMensaje(opcionSeleccionada, rating)
                    )
                }
            }) {
                Icon(Icons.Default.Favorite, contentDescription = "Añadir a favoritos")
            }
        }
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3DAC9))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            MostrarTitulo(modifier = Modifier)

            DropDown(
                selected = opcionSeleccionada,
                onSelected = { opcion ->
                    opcionSeleccionada = opcion
                    rating = 0
                }
            )

            Card(carta)

            Text(
                text = stringResource(R.string.estado),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

            RatingBar(
                currentRating = rating,
                onRatingChanged = { rating = it }
            )
        }
    }
}

@Composable
fun MostrarTitulo(modifier: Modifier = Modifier){
    Text(
        text = stringResource(R.string.app_name),
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp

    )
}
private fun mostrarMensaje(
    nombreCarta: String,
    estado: Int
): String {

    return when (estado) {
        0 -> "Selecciona un número de estrellas"
        1 -> "$nombreCarta calificada como destrozada"
        2 -> "$nombreCarta calificada como usada"
        3 -> "$nombreCarta calificada como nueva"
        else -> "Estado inválido"
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCartas(){
    MostrarEjercicio()
}