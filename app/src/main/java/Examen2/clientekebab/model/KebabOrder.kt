package net.iessochoa.jessicabrotons.clientekebab.model

import androidx.compose.ui.res.stringResource


enum class ProductType{
    DURUM, BOX, PIZZA, PLATO
}

enum class Vegetables{
    TOMATE, LECHUGA, MAIZ, CEBOLLA, ACEITUNAS
}

enum class ProductSize {
    SMALL, MEDIUM, LARGE
}

data class KebabOrder(
    val id: Int = 0,
    val productType: ProductType = ProductType.BOX,
    val clientName: String = "",
    val vegetables: List<Vegetables> = emptyList(),
    val size: ProductSize = ProductSize.MEDIUM,
    val basePrice: Double = 0.0,
    val notes: String = "",
    val delivery: Boolean = false,
    val salsas: String = "Salsa Blanca"
)

fun KebabOrder.productPrice(): Double{
    val precioProducto = when(productType) {
        ProductType.DURUM -> 4.0
        ProductType.BOX -> 5.0
        ProductType.PIZZA -> 6.0
        ProductType.PLATO -> 7.0
    }

    val precioTamaño = when(size){
        ProductSize.SMALL -> 0.0
        ProductSize.MEDIUM -> 1.0
        ProductSize.LARGE -> 2.0
    }

    val ingredientesExtra = vegetables.size * 0.5

    return precioProducto + ingredientesExtra + precioTamaño
}