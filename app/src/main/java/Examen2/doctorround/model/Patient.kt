package net.iessochoa.sergiocontreras.doctorround.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import net.iessochoa.jessicabrotons.posibleexamen.R


/**
 * Estado de evolución del paciente.
 * Útil para mostrar iconos o colores diferentes en la UI según la gravedad.
 */

enum class PatientStatus {
    STABLE,     // Estable
    IMPROVING,  // Mejorando (Responde al tratamiento)
    WORSENING,  // Empeorando (No responde al tratamiento)
    CRITICAL,   // Crítico
    UNKNOWN      //   Pendiente de evaluación
}

/**
 * Representa a un paciente ingresado para la ronda médica.
 *
 * @property id Identificador único (te puede ser útil para la LazyColumn).
 * @property fullName Nombre completo del paciente.
 * @property room Número de habitación (String para permitir letras como "304-B").
 * @property diagnosis Diagnóstico principal (fijo, informativo).
 * @property visitNote Notas de evolución que el médico rellenará (Editable).
 * @property painLevel Nivel de dolor registrado de 0.0 a 10.0 (Editable).
 * @property imageResId Recurso de imagen para el paciente (DrawableRes).
 */
data class Patient(
    val id: Int,
    val fullName: String,
    val room: String,
    val diagnosis: String,
    val status: PatientStatus = PatientStatus.UNKNOWN, // Valor por defecto
    val visitNote: String = "", // Valor por defecto vacío para pacientes nuevos
    val painLevel: Float = 0f,   // Valor por defecto 0 (sin dolor)
    @param:DrawableRes val imageResId: Int // Lo  mas sencillo es que en el modelo esto no sea nulo.
) {

    /** AVISO: Este no es el lugar para tener este tipo de métodos que acoplan el modelo con la vista, pero por no complicar más el examen con funciones de extensión*/

    val statusColor: Color
        get() = when (status) {
            PatientStatus.STABLE -> Color(0xFF4CAF50)    // Verde
            PatientStatus.IMPROVING -> Color(0xFFFFEB3B) // ¡Ojo! El comentario dice Azul pero el color es Amarillo
            PatientStatus.WORSENING -> Color(0xFFFF5722) // Naranja
            PatientStatus.CRITICAL -> Color(0xFFE91E63)  // Rojo
            PatientStatus.UNKNOWN -> Color.Gray
        }

    val painColor: Color
        get() = when (painLevel) {
            in 0f..<1f -> Color(0xFF00B92F)
            in 1f..<2f -> Color(0xFF16BE33)
            in 2f..<3f -> Color(0xFF49C229)
            in 3f..<4f -> Color(0xFF69C51C)
            in 4f..<5f -> Color(0xFFBAD001)
            in 5f..<6f -> Color(0xFFFFCA00)
            in 6f..<7f -> Color(0xFFFF9600)
            in 7f..<8f -> Color(0xFFFF7B00)
            in 8f..<9f -> Color(0xFFFF5F01)
            in 9f..<10f -> Color(0xFFFF000C)
            else -> Color(0xFFFF000C)
        }

    @get:DrawableRes
    val statusIcon: Int
        get() = when (status) {
            PatientStatus.STABLE -> R.drawable.thumb_up_24px
            PatientStatus.IMPROVING -> R.drawable.trending_up_24px
            PatientStatus.WORSENING -> R.drawable.trending_down_24px
            PatientStatus.CRITICAL -> R.drawable.e911_emergency_24px
            PatientStatus.UNKNOWN -> R.drawable.question_mark_24px
        }

    val painMessage: String
        get() = when (painLevel.toInt()) {
            0, 1 -> "Sin dolor"
            2, 3 -> "Dolor leve"
            4, 5 -> "Dolor moderado"
            6, 7 -> "Dolor fuerte"
            8, 9 -> "Dolor muy fuerte"
            else -> "Dolor insoportable"
        }


}