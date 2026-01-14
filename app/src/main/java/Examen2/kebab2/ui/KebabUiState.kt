package net.iessochoa.jessicabrotons.kebab2.ui

import net.iessochoa.jessicabrotons.kebab2.model.KebabOrder

data class KebabUiState(
    val orders: List<KebabOrder> = emptyList(),
    val selectedOrder: KebabOrder? = null
)