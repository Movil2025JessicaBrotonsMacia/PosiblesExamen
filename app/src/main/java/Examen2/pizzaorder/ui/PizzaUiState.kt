package net.iessochoa.jessicabrotons.pizzaorder.ui

import net.iessochoa.jessicabrotons.pizzaorder.data.OrderRepository
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaOrder
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaSize

data class PizzaUiState(
    val selectedOrder: PizzaOrder? = null,
    val selectedSize: PizzaSize = PizzaSize.MEDIUM,
    val selectedIngredients: List<String> = emptyList(),
    val extraCheese: Boolean = false
)
