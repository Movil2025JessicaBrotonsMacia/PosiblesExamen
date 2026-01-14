package net.iessochoa.jessicabrotons.clientekebab.data

import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder

object OrderRepository {

    // Lista interna de pedidos que se van a ir modificando
    private val orders = mutableListOf<KebabOrder>()

    fun getOrders(): List<KebabOrder> = orders

    fun updateOrder(updated: KebabOrder) {
        val index = orders.indexOfFirst { it.id == updated.id }
        if (index != -1) {
           orders[index] = updated
        }
    }
}