package net.iessochoa.jessicabrotons.t05ejercicios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.jessicabrotons.t05ejercicios.ui.components.PedidoPizza
import net.iessochoa.jessicabrotons.t05ejercicios.ui.components.SeleccionColores
import net.iessochoa.jessicabrotons.t05ejercicios.ui.theme.T05EjerciciosTheme
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.CustomDropdownMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T05EjerciciosTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ){
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) //Permite usar Scaffold y TopAppBar
@Composable
fun App(){

    val snackbarHostState = remember { SnackbarHostState() } //Controla el estado de los mensajes emergentes por pantalla
    val scope = rememberCoroutineScope() //Permite lanzar tareas asíncronas, necesrio para el snackBar

    var opciones = stringArrayResource(R.array.opciones).toList() //Opciones de la lista
    var (ejercicioActual, onValueChangeEjercicio) = remember { mutableStateOf(opciones[0]) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(
                title = {Text(text = stringResource(R.string.app_name) + " - " + ejercicioActual)} //Barra superior con el título de la App
        )
        },
        snackbarHost = { SnackbarHost(snackbarHostState)}, //Le decimos donde mostrar el snackBarHost
        floatingActionButton = {
            FloatingActionButton(onClick = { //No se puede llamar al snackBarHostState desde el onClick, por eso creamos el scope
                //muestra el SnackBar desde un hilo
                scope.launch {
                    snackbarHostState.showSnackbar( //Mostrar mensaje y duración
                        message = "Jessica Brotons Maciá - $ejercicioActual",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Mostrar Snackbar") //Mostrar icono dentro del floatingActionButton
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding) // aplica el padding al contenedor
                .fillMaxSize()
        ) {
            //Lógica de los ejercicios
            Body(opciones, ejercicioActual, onValueChangeEjercicio, Modifier.padding(innerPadding))

        }
    }

}

@Composable
fun Body(
    opciones: List<String>,
    ejercicioActual: String,
    onValueChangeEjercicio: (String) -> Unit,
    modifier: Modifier = Modifier
){
    CustomDropdownMenu( //Se llama a la otra clase
        options = opciones, //Opciones posibles de ejercicios
        seleccion = ejercicioActual, //El ejercicio que se ha seleccionado
        label = stringResource(R.string.ejercicios), //Texto del label
        onValueChanged = onValueChangeEjercicio //Función que actualiza el valor.
    )

    if (ejercicioActual == "Ejercicio1"){
        PedidoPizza()
    } else if (ejercicioActual == "Ejercicio2"){
        SeleccionColores(modifier = Modifier)
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    T05EjerciciosTheme {
        App()
    }
}