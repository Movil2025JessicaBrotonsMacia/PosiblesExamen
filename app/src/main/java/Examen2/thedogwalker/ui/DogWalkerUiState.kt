package net.iessochoa.sergiocontreras.thedogwalker.ui

import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

data class DogWalkerUiState(
    val dogs: List<Dog> = DogRepository.getAllDogs(), //Al hacer esto, en el ViewModel no hace falta hacer el bloque init{ para cargar los perros
    val selectedDog : Dog? = null
) {

}