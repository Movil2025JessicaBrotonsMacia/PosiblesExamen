package net.iessochoa.sergiocontreras.mtgmarket.model

import androidx.annotation.DrawableRes

/**
 * Clase de datos inmutable que modela una carta de Magic: The Gathering.
 *
 * Contiene las propiedades esenciales para representar una carta dentro de la aplicación,
 * como su nombre, texto de reglas y la referencia a su imagen. Al ser una `data class`,
 * proporciona automáticamente métodos como `equals()`, `hashCode()` y `toString()`.
 *
 * @property name El nombre de la carta.
 * @property text El texto de reglas o de ambientación de la carta.
 * @property rarity La rareza de la carta (ej. "Común", "Rara", "Mítica").
 * @property expansionSet El código o nombre de la expansión a la que pertenece la carta (ej. "LTR").
 * @property imageId El identificador del recurso drawable (`R.drawable...`) que corresponde a la imagen de la carta.
 *                   La anotación `@DrawableRes` asegura que se pase un ID de recurso válido.
 */
data class MtgCard (
    val name: String,
    val text: String,
    val rarity: String,
    val expansionSet: String,
    @param:DrawableRes val imageId: Int
)
