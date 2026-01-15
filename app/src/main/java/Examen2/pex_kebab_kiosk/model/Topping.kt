package net.iessochoa.rubenexposito.pex_kebab_kiosk.model

import androidx.annotation.DrawableRes

data class Topping (
    val id: String,
    val name: String,
    val price: Double,
    val allergens: List<AllergenType> = emptyList(),
    @param:DrawableRes val imageResId: Int? = null
) {
}