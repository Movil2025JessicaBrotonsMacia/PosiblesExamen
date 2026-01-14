package net.iessochoa.jessicabrotons.pizzaorder.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaSize
import net.iessochoa.jessicabrotons.pizzaorder.model.totalPrice
import net.iessochoa.jessicabrotons.pizzaorder.ui.PizzaViewModel
import net.iessochoa.jessicabrotons.pizzaorder.ui.components.PizzaExtraSwitch
import net.iessochoa.jessicabrotons.pizzaorder.ui.components.PizzaIngredientsRow
import net.iessochoa.jessicabrotons.pizzaorder.ui.components.PizzaOrderActionBar
import net.iessochoa.jessicabrotons.pizzaorder.ui.components.PizzaPriceSummary
import net.iessochoa.jessicabrotons.pizzaorder.ui.components.SizeDropdown

@Composable
fun OrderDetailScreen(
    viewModel: PizzaViewModel,
    onSave: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val pizza = uiState.selectedOrder ?: return
    val context = LocalContext.current
    val totalPrice = pizza.totalPrice()


    Column(Modifier.padding(16.dp)) {

        Text(
            text = "Pedido de ${pizza.customerName} - Pizza ${pizza.pizzaType}",
            style = MaterialTheme.typography.titleLarge
        )

        SizeDropdown(
            selected = uiState.selectedOrder?.size ?: PizzaSize.MEDIUM,
            options = PizzaSize.values().toList(),
            onSizeSelected = { viewModel.onSizeSelected(it) }
        )

        PizzaIngredientsRow(
            ingredients = uiState.selectedIngredients,
            selectedIngredients = uiState.selectedIngredients,
            onIngredientToggle = { viewModel.onIngredientToggle(it) }
        )

        PizzaExtraSwitch(
            label = "Queso extra",
            checked = pizza.extraCheese,
            onCheckedChange = { enabled ->
                viewModel.onExtraCheeseChanged(enabled)
            }
        )

        PizzaPriceSummary(
            totalPrice = totalPrice
        )

        PizzaOrderActionBar(
            onConfirm = {
                viewModel.onSaveOrder()
                onSave()
                Toast.makeText(
                    context,
                    "Se ha guardado el pedido con éxito",
                    Toast.LENGTH_SHORT
                ).show()
                        },
            onCancel = onBack
        )
    }
}

