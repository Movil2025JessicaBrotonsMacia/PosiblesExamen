package net.iessochoa.sergiocontreras.coffeemachine.model

import androidx.annotation.DrawableRes

data class Coffee (
    val productName: String,
    val productPrice: Double,
    @param:DrawableRes val productImage: Int,
    val productDescription: String
)
