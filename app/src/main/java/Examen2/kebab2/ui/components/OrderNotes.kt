package net.iessochoa.jessicabrotons.kebab2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.kebab2.model.KebabOrder


/*
Componente de las notas
 */
@Composable
fun OrderNotes(
    order: KebabOrder,
    onValueChange: (String) -> Unit
){
    Column{
        OutlinedTextField(
            value = order.notes,
            onValueChange = onValueChange,
            label = { Text(text = "Notas del pedido") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar notas"
                )
            },
            singleLine = false,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}