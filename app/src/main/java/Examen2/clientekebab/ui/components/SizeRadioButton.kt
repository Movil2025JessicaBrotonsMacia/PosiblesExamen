package net.iessochoa.jessicabrotons.clientekebab.ui.components

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
import net.iessochoa.jessicabrotons.clientekebab.model.ProductSize

@Composable
fun SizeRadioButton(
    listaSize: List<ProductSize>,
    sizeSeleccionado: ProductSize,
    onSizeSeleccionado: (ProductSize) -> Unit
){
    Column(modifier = Modifier) {

        Text(
            text = "Tamaño del producto",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        listaSize.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = opcion == sizeSeleccionado,
                    onClick = { onSizeSeleccionado(opcion) }
                )

                Text(
                    text = opcion.name,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}