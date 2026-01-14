package net.iessochoa.sergiocontreras.doctorround.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.model.Patient
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme

@Composable
fun PatientListCard(
    patient: Patient,
    onClick: () -> Unit
) {

    val statusColor = patient.statusColor


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = statusColor.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono de estado (Círculo con imagen dentro)
            PatientConditionIcon(patient)

            Spacer(modifier = Modifier.width(16.dp))

            // Datos del paciente
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = patient.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Habitación: ${patient.room}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = patient.diagnosis,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }

            // Indicador si ya ha sido visitado (si tiene nota)
            if(patient.visitNote.isNotEmpty()){
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Visitado",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PatientListCardPreview() {
    val patient = PatientRepository.getPatients().first()
    DoctorRoundTheme() {
        PatientListCard(
            patient = patient,
            onClick = {}
        )
    }
}




