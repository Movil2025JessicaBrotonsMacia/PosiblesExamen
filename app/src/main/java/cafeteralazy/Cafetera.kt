package net.iessochoa.jessicabrotons.cafeteralazy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch
import net.iessochoa.jessicabrotons.cafeteralazy.data.ListaCafes
import net.iessochoa.jessicabrotons.cafeteralazy.model.Cafes
import net.iessochoa.jessicabrotons.posibleexamen.R


@Composable
fun MainCafetera(){


    val layoutDirection = LocalLayoutDirection.current
    val listaOpciones = stringArrayResource(R.array.tipoCafe).toList()
    var tipoSeleccionado by remember { mutableStateOf(listaOpciones[0]) }
    val listaCafes = ListaCafes().loadListaCafes()
    var tipoMensaje = ""

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    // Muestra el mensaje en el Snackbar
                    snackbarHostState.showSnackbar(
                        message = tipoMensaje,
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Mostrar Snackbar")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(
                        start = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateStartPadding(layoutDirection),
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(layoutDirection),
                    )
            ) {

                item {
                    MostrarTitulo()
                }

                items(listaCafes) { cafe ->
                    AffirmationsCard(
                        affirmation = cafe,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

    }

}

@Composable
fun MostrarTitulo(){
    Text(
        text = stringResource(R.string.app_name),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}


private fun mostrarMensaje(
    nombreCafe: String,
    descafeinado: Boolean
): String{

    var mensaje = ""

    if (nombreCafe.isBlank()){
        mensaje = "Por favor, selecciona un café antes de continuar."
    }else if (!descafeinado){
        mensaje = "Preparando $nombreCafe con cafeína..."
    } else if (descafeinado){
        mensaje = "Preparando $nombreCafe descafeinado..."
    }

    return mensaje
}

@Composable
fun AffirmationsCard(
    affirmation: Cafes,
    modifier: Modifier = Modifier
) {
    var tipoSeleccionado by remember { mutableStateOf(affirmation.tipo) }

    Card(modifier = modifier) {
        Column {

            // Imagen
            Image(
                painter = painterResource(affirmation.imagen),
                contentDescription = stringResource(affirmation.nombre),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            // Nombre del café
            Text(
                text = LocalContext.current.getString(affirmation.nombre),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            // ---- AQUÍ VA EL RADIOBUTTON DENTRO DE LA TARJETA ----
            Column(modifier = Modifier.padding(16.dp)) {

                val opciones = listOf("Normal", "Descafeinado")

                opciones.forEach { opcion ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = opcion == tipoSeleccionado,
                            onClick = {
                                tipoSeleccionado = opcion
                                affirmation.tipo = opcion
                            }
                        )

                        Text(
                            text = opcion,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCafetera(){
    MainCafetera()
}