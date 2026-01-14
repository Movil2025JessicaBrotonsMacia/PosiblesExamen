package net.iessochoa.sergiocontreras.doctorround.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.model.Patient
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme

/**
 * Composable que muestra un icono para representar visualmente el estado de un paciente.
 * El ícono está contenido en un círculo con un color de fondo semitransparente
 * que también depende del estado del paciente.
 *
 * @param patient El objeto [Patient] cuya condición se va a mostrar.
 */
@Composable
fun PatientConditionIcon(
    patient: Patient,
    modifier: Modifier = Modifier
) {

    val statusColor = patient.statusColor
    val statusIcon = patient.statusIcon

    Box(
        modifier = modifier
            .size(48.dp)
            .background(statusColor.copy(alpha = 0.3f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(statusIcon),
            contentDescription = "Icono de la condición del paciente",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun PatientConditionIconPreview() {
    val patientPreview = PatientRepository.getPatients().first()
    DoctorRoundTheme() {
        PatientConditionIcon(patientPreview)

    }
}