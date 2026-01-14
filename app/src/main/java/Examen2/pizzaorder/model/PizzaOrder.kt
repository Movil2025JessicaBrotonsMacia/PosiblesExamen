package net.iessochoa.jessicabrotons.pizzaorder.model

data class PizzaOrder(
    val id: Int,
    val customerName: String,

    // Tipo base de pizza (Margarita, BBQ, 4 Quesos…)
    val pizzaType: String,

    // Tamaño seleccionado (dropdown)
    val size: PizzaSize,

    // Ingredientes extra seleccionados
    val ingredients: List<String> = emptyList(),

    // Extra de queso (switch)
    val extraCheese: Boolean = false,

    // Precio base de la pizza según el tipo
    val basePrice: Double,

    // Notas del cliente
    val notes: String = "",

    // Pedido preparado o no
    val ready: Boolean = false
)


enum class PizzaSize {
    SMALL, MEDIUM, LARGE
}

fun PizzaOrder.totalPrice(): Double {
    val sizeExtra = when (size) {
        PizzaSize.SMALL -> 0.0
        PizzaSize.MEDIUM -> 1.0
        PizzaSize.LARGE -> 2.0
    }

    val ingredientsExtra = ingredients.size * 0.5
    val cheeseExtra = if (extraCheese) 1.0 else 0.0

    return basePrice + sizeExtra + ingredientsExtra + cheeseExtra
}

