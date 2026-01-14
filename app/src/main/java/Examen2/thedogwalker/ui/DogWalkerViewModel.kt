package net.iessochoa.sergiocontreras.thedogwalker.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerUiState

class DogWalkerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DogWalkerUiState())

    val uiState: StateFlow<DogWalkerUiState> = _uiState.asStateFlow()

    fun onDogSelected(dog: Dog){
        _uiState.update { currentState ->
            currentState.copy(
                selectedDog = dog
            )
        }
    }

    //Hay que hacerlo con type:String porque se devuelve qué accion ha sido pulsada, y no un boolean
    fun onActionSelected(type: String) {
        _uiState.update { currentState ->

            val dog = currentState.selectedDog

            if(dog == null){
                currentState
            } else{
                val updatedDog = when (type) {
                    "walk" -> dog.copy(isWalked = !dog.isWalked)
                    "pee"  -> dog.copy(hasPeed = !dog.hasPeed)
                    else   -> dog.copy(hasPooped = !dog.hasPooped)
                }

                currentState.copy(
                    //Esto hace que desde la lista de perros se vea que opciones se han seleccionado
                    dogs = currentState.dogs.filter { it.id != dog.id } + updatedDog,
                    selectedDog = updatedDog
                )
            }

        }
    }


//    fun onActionSelected(
//        newWalked: Boolean,
//        newPeed: Boolean,
//        newPoop: Boolean
//    ){
//        _uiState.update { currentState ->
//            var updatedDog = currentState.selectedDog?.copy(
//                isWalked = newWalked,
//                hasPeed = newPeed,
//                hasPooped = newPoop
//            )
//            currentState.copy(
//                selectedDog = updatedDog
//            )
//        }
//    }

}