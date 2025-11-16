package net.iessochoa.jessicabrotons.cartasestado

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import net.iessochoa.jessicabrotons.posibleexamen.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.jessicabrotons.estadocartas.data.Datasource
import net.iessochoa.jessicabrotons.estadocartas.model.Cartas
import net.iessochoa.jessicabrotons.estadocartas.ui.components.EstadoCartas


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstadoDeCartas(

) {
    val layoutDirection = LocalLayoutDirection.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val listaCartas = Datasource().getCartas()

    val listaCartasTitulo = listaCartas.map { it.stringResourceId }
    var cartaSeleccionada by remember { mutableStateOf(listaCartasTitulo[0]) }
    var carta = listaCartas.find { it.stringResourceId == cartaSeleccionada } ?: listaCartas[0]
    var estadoActual by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MostrarTitulo()

            ListaCartas(
                listaCartas = Datasource().getCartas()
            )
        }
    }
}

@Composable
fun MostrarTitulo(modifier: Modifier = Modifier){
    Text(
        text = stringResource(R.string.app_name),
        modifier = modifier
            .padding(top = 30.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )
}

@Composable
fun MostrarCartas(
    estadoActual: Int,
    onEstadoChanged: (Int) -> Unit,
    carta: Cartas,
    modifier: Modifier = Modifier
){

    val listaOpciones = stringArrayResource(R.array.estadoCarta).toList()
    var opcionSeleccionada by remember { mutableStateOf(listaOpciones[0]) }

    Card(modifier = modifier) {
        Column{
            Image(
                painter = painterResource(carta.drawableResourceId),
                contentDescription = stringResource(carta.stringResourceId),
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(carta.stringResourceId),
                modifier = modifier.padding(10.dp),
                fontWeight = FontWeight.Bold
            )

            EstadoCartas(
                currentRating = estadoActual,
                onRatingChanged = onEstadoChanged
            )
        }
    }
}

@Composable
fun ListaCartas(listaCartas: List<Cartas>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(listaCartas) { carta ->

            var estadoActual by remember { mutableStateOf(0) }

            MostrarCartas(
                estadoActual = estadoActual,
                onEstadoChanged = {estadoActual = it},
                carta = carta,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEstadoDeCartas() {
    EstadoDeCartas()
}
