package net.iessochoa.jessicabrotons.saludopersonalizado.ui.components


import android.graphics.fonts.Font
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import net.iessochoa.jessicabrotons.posibleexamen.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){

    var inputNombre by remember { mutableStateOf("") }
    var saludoFormal by remember { mutableStateOf(false) }
    val nombre = inputNombre
    val tipoSaludo = mostrarSaludo(nombre, saludoFormal)
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
                        message = tipoSaludo,
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Icon(Icons.Filled.Send, contentDescription = "Mostrar Snackbar")
            }
        }
    ) { innerPadding ->

        //Contenido principal dentro del Scaffold
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloSaludo()
            EditTextField(
                label = R.string.nombre,
                value = inputNombre,
                onChangeValue = { inputNombre = it },
                modifier = Modifier.padding(top = 30.dp)
            )
            SaludoFormal(
                saludoFormal = saludoFormal,
                onSaludoFormalChanged = { saludoFormal = it },
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }

}

@Composable
fun TituloSaludo(modifier: Modifier = Modifier){
    Text(
        text = stringResource(R.string.titulo),
        modifier = modifier
            .padding(top = 20.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
}


@Composable
fun SaludoFormal(
    saludoFormal: Boolean,
    onSaludoFormalChanged: (Boolean) -> Unit,
    modifier: Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(30.dp)
            .padding(horizontal = 120.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.tipoSaludo))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = saludoFormal,
            onCheckedChange = onSaludoFormalChanged,
        )
    }
}

private fun mostrarSaludo(
    nombre: String,
    saludoFormal: Boolean
): String{

    var saludo = ""

    if (nombre.isBlank()){
        saludo = "Por favor, introduce tu nombre antes de continuar."
    }else if (!saludoFormal){
        saludo = "¡Hola $nombre!"
    } else if (saludoFormal){
        saludo = "Buenos días, Sra. $nombre"
    }

    return saludo
}

@Composable
fun EditTextField(
    @StringRes label: Int,
    value: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onChangeValue,
        label = {Text(stringResource(label))},
        modifier = modifier
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSaludo() {
    App()
}
