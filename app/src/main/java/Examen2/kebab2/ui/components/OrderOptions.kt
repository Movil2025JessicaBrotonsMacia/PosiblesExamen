package net.iessochoa.jessicabrotons.kebab2.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.kebab2.model.OrderStatus
import net.iessochoa.jessicabrotons.kebab2.ui.KebabViewModel

/*
Esto es como si fuera el OrderDetailScreen
 */
@Composable
fun OrderOptions(
    onBack: () -> Unit,
    viewModel: KebabViewModel
) {
    // TODO EXAMEN
    val scrollState = rememberScrollState()

    val uiState by viewModel.uiState.collectAsState()

    val order = uiState.selectedOrder ?: return

    val context = LocalContext.current

    val onNotesChanged: (String) -> Unit = {newNotes ->
        viewModel.onNotesChanged(newNotes)
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pedido de ${order.customerName}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Pedido: ${order.kebabType}",
            style = MaterialTheme.typography.headlineLarge
        )

        StatusDropDown(
            selected = uiState.selectedOrder?.status ?: OrderStatus.PREPARING,
            options = OrderStatus.values().toList(),
            onStatusSelected = {viewModel.onStatusChanged(it)},
        )

        Text(
            text = "Precio: ${order.price}",
            fontWeight = FontWeight.Bold
        )

        PrioritySwitch(
            label = "El pedido es urgente?",
            checked = order.isUrgent,
            onCheckedChange = { enabled ->
                viewModel.onPriorityChanged(enabled)
            }
        )

        OrderNotes(
            order = order,
            onValueChange = onNotesChanged
        )

        //Botón de actualizar pedido
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.onOrderSaved()
                Toast.makeText(
                    context,
                    "Se ha actualizado el pedido",
                    Toast.LENGTH_SHORT
                ).show()
                onBack()
            }
        ) {
            Text("Actualizar pedido")
        }
    }
}