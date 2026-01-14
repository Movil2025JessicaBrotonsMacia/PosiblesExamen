package net.iessochoa.sergiocontreras.doctorround.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.model.Patient
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme

/**
 * Componente para visualizar y editar el nivel de dolor.
 * Muestra un indicador circular visual y un Slider para modificar el valor.
 */
@Composable
fun PatientSymptomatology(
    patient: Patient,
    // TODO: TAREA 4 (UI Detalle)
    // Falta un parámetro fundamental aquí para poder comunicar los cambios al ViewModel.
    onPainValueChange: (Float) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {

        // Cabecera con Indicador Visual (Ya implementado)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Nivel de dolor", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = patient.painMessage, // Propiedad de extensión (ver abajo)
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Indicador Circular del valor (Visual)
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { patient.painLevel / 10f }, // Normalizado a 0.0-1.0
                    color = patient.painColor, // Color dinámico
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Text(
                    text = "%.1f".format(patient.painLevel),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // TODO: TAREA 4 - Implementar Slider
        // Actualmente el Slider es estático (no se mueve).
        // 1. Vincula 'value' al nivel de dolor del paciente.
        // 2. En 'onValueChange', llama a la lambda que has añadido por parámetro.
//        Slider(
//            value = 0f, // <--- ESTO ESTÁ MAL (Hardcoded)
//            onValueChange = { /* TODO: Comunicar cambio */ },
//            valueRange = 0f..10f,
//            steps = 19 // Truco: 19 pasos permiten saltos de 0.5 ( (10-0)/0.5 - 1 = 19 )
//        )

        Slider(
            value = patient.painLevel,
            onValueChange = onPainValueChange,
            valueRange = 0f..10f,
            steps = 19 // Truco: 19 pasos permiten saltos de 0.5 ( (10-0)/0.5 - 1 = 19 )
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun PatientSymptomatologyPreview() {
    val patientPreview = PatientRepository.getPatients().first()
    DoctorRoundTheme() {

    }
}
