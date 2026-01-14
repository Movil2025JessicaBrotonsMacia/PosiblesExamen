package net.iessochoa.jessicabrotons.t04ejercicio3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.jessicabrotons.t04ejercicio3.ui.theme.T04Ejercicio3Theme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T04Ejercicio3Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Calculadora()
                }
            }
        }
    }
}

@Composable
fun Calculadora(modifier: Modifier = Modifier){
    var number1Input by remember { mutableStateOf("") } //Recordar el número que se ha introducido
    var number2Input by remember { mutableStateOf("") }
    val n1 = number1Input.toDoubleOrNull() ?: 0.0 //Pasar el número introducido a double, por defecto pone 0.0
    val n2 = number2Input.toDoubleOrNull() ?: 0.0

    val listaOpciones = stringArrayResource(R.array.operations).toList() //Llamar al array de opciones de strings.xml y lo convierte en una lista para poder iterar los elementos.
    var operacionSeleccionada by remember { mutableStateOf(listaOpciones[0]) } //Variable de estado para almacenar la operación actual elegida. Inicialmente coge el primer elemento de la lista.

    //Depende de la opción seleccionada, se hará una operación u otra.
    val resultado = when (operacionSeleccionada){
        "SUMA" -> calcularSuma(n1, n2)
        "RESTA" -> calcularResta(n1, n2)
        "MULT" -> calcularMult(n1, n2)
        "DIV" -> calcularDiv(n1, n2)
        else -> "0.00"
    }

    Column (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 50.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text( //Título
            text = stringResource(R.string.titulo), //Llamar al texto que va en título
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 30.sp
        )
        EditNumberField(
            label = R.string.num1,
            keyboardOptions = KeyboardOptions.Default.copy( //Mostrar teclado numérico con el icono de next.
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = number1Input, //Valor que se ha introducido
            onValueChanged = {number1Input = it}, //Cuando el usuario introduce un número, se actualiza number1Input.
            modifier = Modifier.padding(bottom = 16.dp)
        )
        EditNumberField(
            label = R.string.num2,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = number2Input,
            onValueChanged = {number2Input = it}, //Cuando el usuario introduce un número, se actualiza number2Input.
            modifier = Modifier.padding(bottom = 16.dp)
        )

        RadioGroupSample(
            listaOpciones = listaOpciones,
            operacionSeleccionada = operacionSeleccionada,
            onOptionSelected = { operacionSeleccionada = it } //Cuando el usuario selecciona una opción, se actualiza operacionSeleccionada.
        )

        MostrarResultado(modifier, resultado) //El resultado lo mostramos aquí

        ImagenSeleccionada(operacionSeleccionada = operacionSeleccionada)

    }
}

@Composable
fun MostrarResultado(
    modifier: Modifier = Modifier,
    resultado: String = "0.0" //Por defecto ponemos 0.0
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text( //Título resultado:
            text = stringResource(R.string.resultado),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 30.sp
        )
        Text(
            modifier = modifier,
            text = resultado,
            color = Color.Blue,
            fontSize = 50.sp
        )
    }

}

@Composable
fun EditNumberField(
    @StringRes label: Int,                   // Recurso de texto (ID) del label, por ejemplo R.string.num1
    keyboardOptions: KeyboardOptions,        // Configuración del teclado (tipo numérico, acción Next/Done, etc.)
    value: String,                           // Valor actual del campo de texto (lo que escribe el usuario)
    onValueChanged: (String) -> Unit,        // Función lambda que se ejecuta cuando el texto cambia
    modifier: Modifier = Modifier
){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = {Text(stringResource(label))},
        singleLine = true,
        modifier = modifier,
        keyboardOptions = keyboardOptions
    )
}

//Función para mostrar las distintas opciones de la calculadora.
@Composable
fun RadioGroupSample( listaOpciones: List<String>,
                      operacionSeleccionada: String,
                      onOptionSelected: (String) -> Unit,
                      modifier: Modifier = Modifier) {

    Row(modifier.selectableGroup()) { //Grupo de opciones para seleccionar
        listaOpciones.forEach { opcion -> //Para cada opción de la lista de opciones...
            Row( //Una fila para cada opción
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (opcion == operacionSeleccionada),
                        onClick = { onOptionSelected(opcion) },
                        role = Role.RadioButton,
                    )
                    .padding(horizontal = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton( //Botón para cada opción
                    selected = (opcion == operacionSeleccionada),
                    onClick = null,
                )
                Text( //Texto de cada RadioButton
                    text = opcion,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 5.dp),
                )
            }
        }

    }

}

@Composable
fun ImagenSeleccionada(operacionSeleccionada : String){
    //Depende de la opción seleccionada, se mostrará una imagen u otra.
    val imagen = when (operacionSeleccionada) {
        "SUMA" -> R.drawable.suma
        "RESTA" -> R.drawable.resta
        "MULT" -> R.drawable.multiplicacion
        "DIV" -> R.drawable.division
        else -> R.drawable.ic_launcher_background
    }
    Image(
        painter = painterResource(imagen), //Llamamos a la variable creada para imprimirla por pantalla.
        contentDescription = "Imagen de la opción", //SIEMPRE poner un contentDescription
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    )
}


private fun calcularSuma(
    numero1: Double,
    numero2: Double
): String {
    var suma = numero1 + numero2
    return NumberFormat.getInstance().format(suma)
}

private fun calcularResta(
    numero1: Double,
    numero2: Double
): String {
    var resta = numero1 - numero2
    return NumberFormat.getInstance().format(resta)
}

private fun calcularMult(
    numero1: Double,
    numero2: Double
): String {
    var mult = numero1 * numero2
    return NumberFormat.getInstance().format(mult)
}

private fun calcularDiv(
    numero1: Double,
    numero2: Double
): String {
    var div = numero1 / numero2
    return NumberFormat.getInstance().format(div)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    T04Ejercicio3Theme {
        Calculadora()
    }
}