package net.iessochoa.jessicabrotons.kebab2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.jessicabrotons.kebab2.data.OrderRepository
import net.iessochoa.jessicabrotons.kebab2.model.KebabOrder
import net.iessochoa.jessicabrotons.kebab2.ui.KebabViewModel
import net.iessochoa.jessicabrotons.kebab2.ui.components.OrderSummaryCard

@Composable
fun OrderListScreen(
    viewModel: KebabViewModel,
    onOrderClick: (KebabOrder) -> Unit,
    modifier: Modifier = Modifier
) {
//    // ❌ MALA PRÁCTICA
//    val orders = OrderRepository.getOrders()

    //Observamos el estado
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text("PEDIDOS: ${uiState.orders.size}")


        // TODO EXAMEN
        // Refactorizar para usar ViewModel y StateFlow

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.orders) { order ->
                OrderSummaryCard(
                    order = order,
                    onClick = {
                        viewModel.onOrderSelected(order)
                        onOrderClick(order)
                    }
                )
            }
        }
    }

}
