package cafeteraradio.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CantidadAzucar(
    listaOpciones: List<String>,
    opcionSeleccionada: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = "Cantidad de azúcar",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 50.dp),
            textAlign = TextAlign.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 1.dp)
        ) {
        listaOpciones.forEach { opcion ->

                RadioButton(
                    selected = opcion == opcionSeleccionada,
                    onClick = { onOptionSelected(opcion) }
                )
                Text(
                    text = opcion,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }

}