package net.iessochoa.jessicabrotons.kebab2.data

import net.iessochoa.jessicabrotons.kebab2.model.KebabOrder
import net.iessochoa.jessicabrotons.kebab2.model.OrderStatus

object OrderRepository {

    private val orders = mutableListOf(
        KebabOrder(
            id = 1,
            customerName = "Ana",
            kebabType = "Durum",
            size = "Grande",
            extraMeat = false,
            price = 9.50,
            status = OrderStatus.PREPARING,
            isUrgent = false,
            notes = "",
            managed = false
        ),
        KebabOrder(
            id = 2,
            customerName = "Luis",
            kebabType = "Pan",
            size = "Mediano",
            extraMeat = true,
            price = 11.00,
            status = OrderStatus.READY,
            isUrgent = true,
            notes = "Cliente esperando",
            managed = true
        ),
        KebabOrder(
            id = 3,
            customerName = "Marta",
            kebabType = "Plato",
            size = "Pequeño",
            extraMeat = false,
            price = 8.00,
            status = OrderStatus.DELIVERED,
            isUrgent = false,
            notes = "",
            managed = true
        )
    )

    fun getOrders(): List<KebabOrder> = orders

    fun getOrderById(id: Int): KebabOrder? =
        orders.find { it.id == id }

    fun updateOrder(updated: KebabOrder) {
        val index = orders.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            orders[index] = updated
        }
    }
}