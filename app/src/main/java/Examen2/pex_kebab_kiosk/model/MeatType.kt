package net.iessochoa.rubenexposito.pex_kebab_kiosk.model

import android.view.Display

enum class MeatType(
    val id: String,
    val displayName: String
) {
    BEEF("beef", "Ternera"),
    CHICKEN("chicken", "Pollo"),
    MIXED("mixed","Mixto")
}