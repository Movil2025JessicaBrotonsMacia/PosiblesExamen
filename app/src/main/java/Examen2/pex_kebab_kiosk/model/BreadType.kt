package net.iessochoa.rubenexposito.pex_kebab_kiosk.model

import androidx.annotation.DrawableRes
import net.iessochoa.jessicabrotons.posibleexamen.R

enum class BreadType(
    val displayName: String,
    val price: Double,
    @DrawableRes val imageResId: Int
) {
    PITA("Pita", 4.00, R.drawable.ic_bread_pita),
    DURUM("Durum", 5.50, R.drawable.ic_bread_durum)
}