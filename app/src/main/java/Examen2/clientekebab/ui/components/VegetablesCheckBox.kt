package net.iessochoa.jessicabrotons.clientekebab.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.clientekebab.model.Vegetables

@Composable
fun VegetablesCheckBox(
    listaOpciones: List<Vegetables>,
    vegetableSeleccionado: Set<Vegetables>,
    onOptionChecked: (Vegetables) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier
            .selectableGroup()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        listaOpciones.forEach { opcion ->
            Row(
                Modifier
                    .height(40.dp)
                    .padding(horizontal = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = (vegetableSeleccionado.contains(opcion)),
                    onCheckedChange = {onOptionChecked(opcion)}
                )
                Text(
                    text = opcion.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVegetablesCheckBox(){
    VegetablesCheckBox(
        listaOpciones = Vegetables.values().toList(),
        vegetableSeleccionado = setOf(),
        onOptionChecked = {  }
    )
}

