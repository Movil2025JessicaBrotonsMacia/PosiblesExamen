package net.iessochoa.sergiocontreras.doctorround.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.model.Patient
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme

/* Este componente se entrega ya implementado */

@Composable
fun PatientDiagnosis(
    patient: Patient,
    onValueChange: (String) -> Unit
) {

    Column {

        Text("Diagnóstico: ${patient.diagnosis}")

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = patient.visitNote,
            onValueChange = onValueChange,
            label = { Text (text = "NOTAS DE LA RONDA")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar nota"
                )
            },
            singleLine = false,
            maxLines = 5,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun PatientDiagnosisPreview() {
    val patientPreview = PatientRepository.getPatients().first()
    DoctorRoundTheme() {
        PatientDiagnosis(patientPreview) { }
    }
}