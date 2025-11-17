package net.iessochoa.jessicabrotons.cafeteraradio.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafeteraradio.ui.components.CantidadAzucar
import kotlinx.coroutines.launch

import net.iessochoa.jessicabrotons.posibleexamen.R


@Composable
fun MainCafetera(){

    //RADIOBUTTON
    val listaOpciones = stringArrayResource(R.array.tipoCafe).toList()
    var tipoSeleccionado by remember { mutableStateOf(listaOpciones[0]) }

    val listaAzucar = stringArrayResource(R.array.cantidadAzucar).toList()
    var azucarSeleccionado by remember { mutableStateOf(listaAzucar[0]) }

    //SWITCH
    var descafeinado by remember { mutableStateOf(false) }

    //FAB
    val tipoMensaje = mostrarMensaje(tipoSeleccionado, descafeinado)
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

        Column( //Centrar toda la columna al centro
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MostrarTitulo()
            TipoCafe( //RadioButton
                listaOpciones = listaOpciones,
                tipoSeleccionado = tipoSeleccionado,
                onOptionSelected = { tipoSeleccionado = it }
            )
            CantidadAzucar(
                listaOpciones = listaAzucar,
                opcionSeleccionada = azucarSeleccionado,
                onOptionSelected = {azucarSeleccionado = it}
            )
            Descafeinado( //Switch
                descafeinado = descafeinado,
                onDescafeinadoChanged = {descafeinado = it},
                modifier = Modifier
            )
            ImagenSeleccionada(
                cafeSeleccionado = tipoSeleccionado
            )
        }
    }
}

@Composable
fun MostrarTitulo(){
    Text(
        text = stringResource(R.string.app_name),
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

//SWITCH
@Composable
fun Descafeinado(
    descafeinado: Boolean,
    onDescafeinadoChanged: (Boolean) -> Unit,
    modifier: Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(30.dp)
            .padding(horizontal = 120.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.descafeinado))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = descafeinado, //true o false
            onCheckedChange = onDescafeinadoChanged,
        )
    }
}

fun mostrarMensaje(
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
fun ImagenSeleccionada(cafeSeleccionado: String){
    val imagen = when (cafeSeleccionado){
        "Solo" -> R.drawable.solo
        "Cortado" -> R.drawable.cortado
        "Con Leche" -> R.drawable.leche
        "Bombón" -> R.drawable.bombon
        else -> R.drawable.vacio
    }

    Image(
        painter = painterResource(imagen),
        contentDescription = cafeSeleccionado,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .size(300.dp) //Tamaño de la imagen
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCafetera(){
    MainCafetera()
}