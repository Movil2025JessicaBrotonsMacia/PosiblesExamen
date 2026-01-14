package net.iessochoa.jessicabrotons.clientekebab.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.jessicabrotons.clientekebab.model.ProductSize
import net.iessochoa.jessicabrotons.clientekebab.model.ProductType
import net.iessochoa.jessicabrotons.clientekebab.model.Vegetables

@Composable
fun NameTextField(
    kebabOrder: KebabOrder,
    onValueChange: (String) -> Unit
){
    Column{
        Text("Nombre del pedido")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = kebabOrder.clientName,
            onValueChange = onValueChange,
            label = {Text(text = "Escribe tu nombre del pedido")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar nombre"
                )
            },
            singleLine = false,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun PatientDiagnosisPreview() {
    val fakeOrder = KebabOrder(
        id = 1,
        productType = ProductType.DURUM,
        clientName = "Jessica",
        vegetables = listOf(
            Vegetables.TOMATE,
            Vegetables.LECHUGA,
            Vegetables.CEBOLLA
        ),
        size = ProductSize.MEDIUM,
        basePrice = 3.0,
        notes = "Sin cebolla y poco picante",
        delivery = true
    )


    OrderNotes(
        kebabOrder = fakeOrder,
        onValueChange = { }
    )
}