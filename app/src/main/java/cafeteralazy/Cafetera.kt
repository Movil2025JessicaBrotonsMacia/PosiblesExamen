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
    val listaCafes = ListaCafes().loadListaCafes()

    var cafeSeleccionado by remember { mutableStateOf<Cafes?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {

                    val mensaje = if (cafeSeleccionado == null) {
                        "Por favor, selecciona un café antes de continuar."
                    } else {
                        // Si ya has actualizado tu data class, esto NO será null
                        if (cafeSeleccionado!!.tipo == "Normal") {
                            "Preparando ${cafeSeleccionado!!.nombre} con cafeína..."
                        } else {
                            "Preparando ${cafeSeleccionado!!.nombre} descafeinado..."
                        }
                    }

                    snackbarHostState.showSnackbar(
                        message = mensaje,
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
                        cafeSeleccionado = cafeSeleccionado,
                        onSeleccionar = { cafeSeleccionado = it },
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
@Composable
fun AffirmationsCard(
    affirmation: Cafes,
    cafeSeleccionado: Cafes?,
    onSeleccionar: (Cafes) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {

            // Imagen
            Image(
                painter = painterResource(affirmation.imagen),
                contentDescription = affirmation.nombre,   // ← AHORA CORRECTO
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            // Nombre del café
            Text(
                text = affirmation.nombre,                // ← AHORA CORRECTO
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            // RadioButton Normal / Descafeinado
            Column(modifier = Modifier.padding(16.dp)) {

                val opciones = listOf("Normal", "Descafeinado")

                opciones.forEach { opcion ->
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        RadioButton(
                            selected = cafeSeleccionado?.nombre == affirmation.nombre &&
                                    affirmation.tipoState == opcion,

                            onClick = {
                                affirmation.tipoState = opcion
                                onSeleccionar(affirmation)
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