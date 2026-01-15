package net.iessochoa.rubenexposito.pex_kebab_kiosk

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.rubenexposito.pex_kebab_kiosk.data.ToppingRepository
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.BreadType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.MeatType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.Topping

class KebabKioskViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KebabKioskUiState())
    val uiState: StateFlow<KebabKioskUiState> = _uiState.asStateFlow()
    /*
        init {
            loadInitialData()
        }

       fun loadInitialData() {
            val toppings = ToppingRepository.getAllToppings()

            _uiState.update { currentState ->
                currentState.copy(
                    availableToppings = toppings
                )
            }
        }*/

    fun onBreadSelect(bread: BreadType) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedBread = bread
            )
        }
    }

    fun onMeatSelect(meat: MeatType) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMeat = meat
            )
        }
    }


    fun toggleTopping(topping: Topping) {
        _uiState.update { currentState ->
            val updatedToppings = if (currentState.selectedToppings.contains(topping)) {
                currentState.selectedToppings - topping
            } else {
                currentState.selectedToppings + topping
            }
            currentState.copy(selectedToppings = updatedToppings)
        }
        setTotal()
    }

    fun onConfirmOrder(newBread: BreadType, newMeat: MeatType, newTopping: Set<Topping>) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedBread = newBread,
                selectedMeat = newMeat,
                selectedToppings = newTopping
            )
        }
    }

    fun setTotal() {
        _uiState.update { currentState ->

//            it.copy(
//                totalPrice = (it.selectedBread?.price
//                    ?: 0.0) + it.selectedToppings.sumOf { it.price })

            currentState.copy(
                totalPrice = (currentState.selectedBread?.price ?: 0.0) +
                        currentState.selectedToppings.sumOf { it.price }
            )
        }
    }

    fun resetOrder() {
        _uiState.value = KebabKioskUiState()
    }
}
