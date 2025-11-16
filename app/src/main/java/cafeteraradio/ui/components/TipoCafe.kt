package net.iessochoa.jessicabrotons.cafeteraradio.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TipoCafe(
    listaOpciones: List<String>,
    tipoSeleccionado: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(top = 10.dp)) {

        Text(
            text = "Tipo de café",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp)
        )

        listaOpciones.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = opcion == tipoSeleccionado,
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