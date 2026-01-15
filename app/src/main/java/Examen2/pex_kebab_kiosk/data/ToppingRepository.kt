package net.iessochoa.rubenexposito.pex_kebab_kiosk.data

import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.AllergenType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.Topping
import kotlin.random.Random

object ToppingRepository {
    private val toppings = listOf(
        Topping(
            id = "salsa_blanca",
            name = "Salsa Blanca",
            price = 0.0,
            allergens = listOf(
                AllergenType.DAIRY
            )
        ),

        Topping(
            id = "salsa_roja",
            name = "Salsa Roja (Picante)",
            price = 0.0,
            allergens = listOf(
                AllergenType.SPICY
            )
        ),

        Topping(
            id = "huevo",
            name = "Huevo",
            price = 0.50,
            allergens = listOf(
                AllergenType.EGG
            )
        ),

        Topping(
            id = "pistachos",
            name = "Pistachos (Crujiente)",
            price = 1.20,
            allergens = listOf(
                AllergenType.NUTS
            )
        ),

        Topping(
            id = "gambas",
            name = "Gambas (Mar y Montaña)",
            price = 2.00,
            allergens = listOf(
                AllergenType.SEAFOOD
            )
        )
    )
    fun getAllToppings(): List<Topping> {
        return toppings
    }

    fun getRandomTopic(): Topping = toppings[Random.nextInt(toppings.size)]
}