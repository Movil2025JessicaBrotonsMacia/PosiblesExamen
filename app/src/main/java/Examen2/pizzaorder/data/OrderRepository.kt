package net.iessochoa.jessicabrotons.pizzaorder.data

import androidx.compose.runtime.mutableStateListOf
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaOrder
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaSize


object OrderRepository {

    private val _orders = mutableStateListOf(
        PizzaOrder(
            id = 1,
            customerName = "Ana",
            pizzaType = "Margarita",
            size = PizzaSize.MEDIUM,
            ingredients = listOf("Queso", "Tomate"),
            extraCheese = false,
            basePrice = 8.0,
            notes = "",
            ready = false
        ),
        PizzaOrder(
            id = 2,
            customerName = "Luis",
            pizzaType = "Pepperoni",
            size = PizzaSize.LARGE,
            ingredients = listOf("Pepperoni"),
            extraCheese = true,
            basePrice = 10.0,
            notes = "Sin cebolla",
            ready = true
        ),
        PizzaOrder(
            id = 3,
            customerName = "Marta",
            pizzaType = "Barbacoa",
            size = PizzaSize.SMALL,
            ingredients = listOf("Pollo", "BBQ"),
            extraCheese = false,
            basePrice = 9.0,
            notes = "",
            ready = false
        )
    )

    val orders: List<PizzaOrder>
        get() = _orders

    fun updateOrder(updatedOrder: PizzaOrder) {
        val index = _orders.indexOfFirst { it.id == updatedOrder.id }
        if (index != -1) {
            _orders[index] = updatedOrder
        }
    }
}