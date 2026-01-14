package net.iessochoa.jessicabrotons.clientekebab.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.jessicabrotons.clientekebab.data.OrderRepository
import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder
import net.iessochoa.jessicabrotons.clientekebab.model.ProductSize
import net.iessochoa.jessicabrotons.clientekebab.model.ProductType
import net.iessochoa.jessicabrotons.clientekebab.model.Vegetables
import net.iessochoa.jessicabrotons.clientekebab.model.productPrice

open class ClienteKebabViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ClienteKebabUiState())

    val uiState: StateFlow<ClienteKebabUiState> = _uiState.asStateFlow()

    fun onOrderSelected(kebabOrder: KebabOrder){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = kebabOrder
            )
        }
    }


    fun onNameChanged(newName: String){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    clientName = newName
                )
            )
        }
    }
    fun onProductSelected(newProduct: ProductType){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    productType = newProduct
                )
            )
        }
    }

    fun onSizeSelected(newSize: ProductSize){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    size = newSize
                )
            )
        }
    }

    fun onIngredientSelected(ingrediente : Vegetables){
        _uiState.update { currentState ->
            //Si en la lista de ingredientes ya tenemos el ingredietne seleccionado, lo quitamos
            val ingredienteActualizado = if (currentState.currentOrder.vegetables.contains(ingrediente)){
                currentState.currentOrder.vegetables - ingrediente
            } else { //Si lo hemos seleccionado y no estaba en la lista,
                currentState.currentOrder.vegetables + ingrediente
            }

            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    vegetables = ingredienteActualizado
                )
            )
        }
    }

    fun onDeliverChanged(enabled: Boolean){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    delivery = enabled
                )
            )
        }
    }

    fun onSalsasChanged(newSalsa: String){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    salsas = newSalsa
                )
            )
        }
    }

    fun onNoteChanged(newNote: String){
        _uiState.update { currentState ->
            currentState.copy(
                currentOrder = currentState.currentOrder.copy(
                    notes = newNote
                )
            )
        }
    }

    //GUARDAR SIN SOBREESCRIBIR SI ES EL MISMO
//    fun onOrderSaved(){
//        _uiState.update { currentState ->
//
//            val newOrder = currentState.currentOrder.copy(
//                id = currentState.orders.size + 1,
//                basePrice = currentState.currentOrder.productPrice()
//            )
//
//            currentState.copy(
//                orders = currentState.orders + newOrder,
//                currentOrder = KebabOrder() //Limpia el formulario para permitir crear otro pedido con lso valor por defecto
//            )
//        }
//    }

    fun onOrderSaved() {
        _uiState.update { currentState ->

            val updatedOrder = currentState.currentOrder.copy(
                basePrice = currentState.currentOrder.productPrice()
            )

            val updatedOrders =
                if (updatedOrder.id == 0) {
                    // 👉 Pedido nuevo
                    currentState.orders + updatedOrder.copy(
                        id = currentState.orders.size + 1
                    )
                } else {
                    // 👉 Editar pedido existente
                    currentState.orders.map { order ->
                        if (order.id == updatedOrder.id) updatedOrder else order
                    }
                }

            currentState.copy(
                orders = updatedOrders,
                currentOrder = KebabOrder()
            )
        }
    }


}