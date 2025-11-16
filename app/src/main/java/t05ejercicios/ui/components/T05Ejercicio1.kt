package net.iessochoa.jessicabrotons.t05ejercicios.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.jessicabrotons.t05ejercicios.ui.theme.T05EjerciciosTheme


@Composable
fun PedidoPizza(){

    val listaOpciones = stringArrayResource(R.array.ingredientes).toList()
    var ingredienteSeleccionado by remember { mutableStateOf(setOf<String>())} //Pongo setOf(String) para poder poner el .size

    val precioBase = 10;
    val total = precioBase + (ingredienteSeleccionado.size * 2) //Cada vez que se selecciona un ingrediente, se suma 2

    Column ( //NO PONER VERTICALSCROLL NUNCAAA
        modifier = Modifier
            .background(color = Color(0xFFFCE9D2))
            .padding(bottom = 80.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text( //Título de la app
            text = stringResource(R.string.titulo),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 30.sp
        )
        CheckGroupSample( //Lista de checks
            listaOpciones = listaOpciones,
            ingredienteSeleccionado = ingredienteSeleccionado, //Conjunto de ingredientes seleccionados
            onOptionChecked = { opcion ->
                ingredienteSeleccionado = if (ingredienteSeleccionado.contains(opcion)){
                    ingredienteSeleccionado - opcion //Si el ingrediente ya estaba seleccionado, al pulsarlo lo quito.
                } else {
                    ingredienteSeleccionado + opcion //Si no estaba en la lista, lo añado.
                }
            }
        )
        ResultadoTotal(Modifier, "$total €")
        ImagenSeleccionada(ingredienteSeleccionado = ingredienteSeleccionado)
    }
}

@Composable
fun ResultadoTotal(
    modifier: Modifier = Modifier,
    resultado: String = "0" //Resultado por defecto
){
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text( //Total:
            text = stringResource(R.string.total),
            modifier = Modifier
                .padding(bottom = 20.dp, start = 20.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayLarge,
            fontSize = 30.sp
        )
        Text(
            modifier = modifier
                .padding(bottom = 20.dp, start = 10.dp),
            text = resultado,
            style = MaterialTheme.typography.displayLarge,
            fontSize = 30.sp
        )
    }
}

@Composable
fun CheckGroupSample(
    listaOpciones: List<String>, //Lista de posibles opciones
    ingredienteSeleccionado: Set<String>, //Ingredientes seleccionados se añaden a un set
    onOptionChecked: (String) -> Unit,
    modifier: Modifier = Modifier){

    Column(modifier.selectableGroup()
        .fillMaxWidth(),
        horizontalAlignment = Alignment.Start){
        listaOpciones.forEach { opcion ->
            Row(
                Modifier
                    .height(56.dp)
                    .padding(horizontal = 7.dp),
                verticalAlignment = Alignment.CenterVertically //Centrar texto con checkbox
            ){
                Checkbox(
                    checked = (ingredienteSeleccionado.contains(opcion)),
                    onCheckedChange = { onOptionChecked(opcion) } //Define que hacer cuando cambia un checkbox
                )
                Text(
                    text = opcion,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
fun ImagenSeleccionada(ingredienteSeleccionado: Set<String>){ //Recibe el set de los ingredientes seleccionados
    if (ingredienteSeleccionado.isEmpty()){ //Si no hay ninguna, dibujar pizza
        Image(
            painter = painterResource(R.drawable.pizzabase),
            contentDescription = "Pizza base",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 1.dp)
        )
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            ingredienteSeleccionado.forEach { ingrediente -> //Dibujar el ingrediente que hay en el Set
                val imagen = when (ingrediente){
                    "Chorizo" -> R.drawable.chorizoo
                    "Salchicha" -> R.drawable.salchichaa
                    "Pollo" -> R.drawable.pollooo
                    else -> R.drawable.pizzabase
                }
                Image( //Mostrar cada imagen
                    painter = painterResource(imagen), //Variable del when
                    contentDescription = "Pizza",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )
            }
        }
    }




}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PizzaPreview() {
    T05EjerciciosTheme {
        PedidoPizza()
    }
}