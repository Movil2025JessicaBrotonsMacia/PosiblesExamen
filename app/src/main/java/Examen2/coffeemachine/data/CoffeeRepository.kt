package net.iessochoa.sergiocontreras.coffeemachine.data

import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.coffeemachine.model.Coffee

// Esta clase simula una fuente de datos (como una BBDD o una API)
// Es un stub/driver para proveer datos falsos (fake data).
// Usamos 'object' para crear un Singleton, asegurando una única instancia de este repositorio.
// A futuro, considera convertirlo en una 'class' para facilitar la inyección de dependencias
// (por ejemplo, pasarle un DAO de Room).

object CoffeeRepository {

    // Lista privada de cafés (nuestra "base de datos" falsa)
    private val coffees = listOf(
        Coffee(
            productName = "Espresso",
            productPrice = 1.80,
            productImage = R.drawable.coffee_latte,
            productDescription = "Café solo"
        ),
        Coffee(
            productName = "Late",
            productPrice = 1.80,
            productImage = R.drawable.coffee_espresso,
            productDescription = "Café con leche"
        ),
        Coffee(
            productName = "Cappuccino",
            productPrice = 2.75,
            productImage = R.drawable.coffee_cappuccino,
            productDescription = "Café con leche y espuma"

        )
    )

    /**
     * Devuelve la lista completa de cafés.
     */
    fun getCoffees(): List<Coffee> {
        return coffees
    }

    /**
     * Devuelve un café aleatorio de nuestra lista.
     */
    fun getNextCoffee(): Coffee {
        return coffees.random()
    }
}
