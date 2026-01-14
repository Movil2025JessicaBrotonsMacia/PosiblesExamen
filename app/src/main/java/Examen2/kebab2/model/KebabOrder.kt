package net.iessochoa.jessicabrotons.kebab2.model

enum class OrderStatus {
    PREPARING,
    READY,
    DELIVERED{}
}

data class KebabOrder(
    val id: Int,
    val customerName: String,
    val kebabType: String,
    val size: String,
    val extraMeat: Boolean,
    val price: Double,

    // Gestión (dueño)
    val status: OrderStatus,
    val isUrgent: Boolean,
    val notes: String,
    val managed: Boolean
)
