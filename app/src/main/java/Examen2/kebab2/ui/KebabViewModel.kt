package net.iessochoa.jessicabrotons.kebab2.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.jessicabrotons.kebab2.data.OrderRepository
import net.iessochoa.jessicabrotons.kebab2.model.KebabOrder
import net.iessochoa.jessicabrotons.kebab2.model.OrderStatus

class KebabViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KebabUiState())
    val uiState: StateFlow<KebabUiState> = _uiState.asStateFlow()

    // Bloque de inicialización para cargar los pacientes
    init {
        _uiState.update { currentState ->
            currentState.copy(
                orders = OrderRepository.getOrders()
            )
        }
    }

    fun onOrderSelected(order: KebabOrder) {
        // TODO EXAMEN
        _uiState.update { currentState ->
            currentState.copy(
                selectedOrder = order
            )
        }
    }

    fun onStatusChanged(newStatus: OrderStatus) {
        // TODO EXAMEN
        _uiState.update { currentState ->
            val updatedOrder = currentState.selectedOrder?.copy(
                status = newStatus
            )
            currentState.copy(
                selectedOrder = updatedOrder
            )
        }
    }

    fun onPriorityChanged(isUrgent: Boolean) {
        // TODO EXAMEN
        _uiState.update { currentState ->
            val updatedOrder = currentState.selectedOrder?.copy(
                isUrgent = isUrgent
            )
            currentState.copy(
                selectedOrder = updatedOrder
            )
        }
    }

    fun onNotesChanged(notes: String) {
        // TODO EXAMEN
        _uiState.update { currentState ->
            val updatedOrder = currentState.selectedOrder?.copy(
                notes = notes
            )
            currentState.copy(
                selectedOrder = updatedOrder
            )
        }
    }

    fun onOrderSaved() {
        // TODO EXAMEN
        val currentOrder = _uiState.value.selectedOrder
        if (currentOrder != null){
            OrderRepository.updateOrder(currentOrder)
        }
    }
}