package net.iessochoa.sergiocontreras.doctorround.model

import androidx.annotation.DrawableRes

data class PatientUiState(
    val id: Int = 0,
    val fullName: String = "",
    val room: String = "",
    val diagnosis: String = "",
    val status: PatientStatus = PatientStatus.UNKNOWN, // Valor por defecto
    val visitNote: String = "", // Valor por defecto vacío para pacientes nuevos
    val painLevel: Float = 0f,   // Valor por defecto 0 (sin dolor)
    @param:DrawableRes val imageResId: Int // Lo  mas sencillo es que en el modelo esto no sea nulo.
)
