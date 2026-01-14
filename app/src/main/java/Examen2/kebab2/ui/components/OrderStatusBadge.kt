package net.iessochoa.jessicabrotons.kebab2.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import net.iessochoa.jessicabrotons.kebab2.model.OrderStatus

@Composable
fun OrderStatusBadge(status: OrderStatus) {
    Text(text = status.name)
}