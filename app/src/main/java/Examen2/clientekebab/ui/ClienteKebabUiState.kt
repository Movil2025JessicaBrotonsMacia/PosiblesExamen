package net.iessochoa.jessicabrotons.clientekebab.ui

import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder
import net.iessochoa.jessicabrotons.clientekebab.model.ProductSize
import net.iessochoa.jessicabrotons.clientekebab.model.ProductType

data class ClienteKebabUiState(
    val currentOrder: KebabOrder = KebabOrder(),
    val orders: List<KebabOrder> = emptyList(),
)