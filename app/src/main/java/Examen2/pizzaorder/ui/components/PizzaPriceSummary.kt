package net.iessochoa.jessicabrotons.pizzaorder.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
        /* TODO: El precio no se calcula correctamente */
fun PizzaPriceSummary(
    totalPrice: Double,
) {

    Text(
        text = "Total: $${totalPrice}",
        style = MaterialTheme.typography.titleLarge
    )
}
