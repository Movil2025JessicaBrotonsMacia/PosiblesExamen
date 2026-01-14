package net.iessochoa.sergiocontreras.coffeemachine.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.coffeemachine.data.CoffeeRepository
import net.iessochoa.sergiocontreras.coffeemachine.model.Coffee

@Composable
fun CoffeeList(
    listaOpciones: List<Coffee>,
    cafeSeleccionado: Coffee,
    onOptionSelected: (Coffee) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        listaOpciones.forEach { opcion ->
            CoffeeSelector(
                coffee = opcion,
                selected = opcion == cafeSeleccionado,
                onSelected = {onOptionSelected(opcion)}
            )
        }
    }


}

//@Preview(showBackground = true)
//@Composable
//fun CoffeeListPreview() {
//    CoffeeList(
//
//    )
//}