package net.iessochoa.jessicabrotons.kebab2.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/*
Componente Switch para marcar si un pedido es urgente o no
 */
@Composable
fun PrioritySwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        Spacer(Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}