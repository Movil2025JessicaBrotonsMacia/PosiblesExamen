package net.iessochoa.jessicabrotons.pizzaorder.ui
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.jessicabrotons.pizzaorder.data.OrderRepository
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaOrder
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaSize

class PizzaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PizzaUiState())
    val uiState: StateFlow<PizzaUiState> = _uiState.asStateFlow()

    private val EXTRA_CHEESE_PRICE = 1.0


    fun onOrderSelected(order : PizzaOrder) {
       _uiState.update { currentState ->
           currentState.copy(
               selectedOrder = order
           )
       }
    }

    fun onSizeSelected(newSize: PizzaSize) {
        // ❌ NO usa copy
        // ❌ Usa String en vez de enum
        _uiState.update { currentState ->
            val updatedOrder = currentState.selectedOrder?.copy(
                size = newSize
            )
            currentState.copy(
                selectedOrder = updatedOrder
            )
        }
    }

    /* TODO: Añadir o quitar ingrediente */
    fun onIngredientToggle(ingredient: String) {
        // INCOMPLETO
        _uiState.update { currentState ->
            val updatedIngredients =
                if (ingredient in currentState.selectedIngredients){ //Si el ingrediente está, lo quitamos
                    currentState.selectedIngredients - ingredient
                } else { //Si no, lo añadimos
                    currentState.selectedIngredients + ingredient
                }
            currentState.copy(
                selectedIngredients = updatedIngredients
            )
        }
    }

    //Activar o desactivar queso extra
    fun onExtraCheeseChanged(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedOrder = currentState.selectedOrder?.copy(
                    extraCheese = enabled
                )
            )
        }
    }

    fun onSaveOrder() {
        // ❌ No actualiza la lista
        // ❌ No vuelve atrás
        val currentOrder = _uiState.value.selectedOrder
        if (currentOrder != null){ //Si hay alguna order seleccionada
            OrderRepository.updateOrder(currentOrder)
        }
    }


    private fun calculateTotalPrice(
        basePrice: Double,
        ingredientsCount: Int,
        extraCheese: Boolean
    ): Double {
        val ingredientsPrice = ingredientsCount * 0.5
        val extraCheesePrice = if (extraCheese) 1.0 else 0.0

        return basePrice + ingredientsPrice + extraCheesePrice
    }


}
