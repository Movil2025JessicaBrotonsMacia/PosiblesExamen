package net.iessochoa.jessicabrotons.pizzaorder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import net.iessochoa.jessicabrotons.pizzaorder.data.OrderRepository
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaOrder
import net.iessochoa.jessicabrotons.pizzaorder.ui.PizzaOrderTopAppBar
import net.iessochoa.jessicabrotons.pizzaorder.ui.PizzaViewModel
import net.iessochoa.jessicabrotons.pizzaorder.ui.components.OrderListCard

@Composable
fun OrderListScreen(
    viewModel: PizzaViewModel,
    onOrderClick: (PizzaOrder) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val orders = OrderRepository.orders

    Column {
        Text("Pedidos: ${orders.size}") // 🔍 DEBUG (importante)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(orders) { pizza ->
                OrderListCard(
                    order = pizza,
                    onClick = { onOrderClick(pizza) }
                )
            }
        }
    }
}

