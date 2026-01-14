package net.iessochoa.sergiocontreras.doctorround.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.ui.DoctorViewModel
import net.iessochoa.sergiocontreras.doctorround.ui.components.PatientDiagnosis
import net.iessochoa.sergiocontreras.doctorround.ui.components.PatientSummaryCard
import net.iessochoa.sergiocontreras.doctorround.ui.components.PatientSymptomatology
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun PatientDetailScreen(
    // TODO: TAREA 2 y 3 - Modificar Argumentos para MVVM y Navegación
    // 1. Elimina 'patient'. La pantalla debe obtener los datos del ViewModel.
    // 2. Añade 'doctorViewModel: DoctorViewModel'.
    // 3. Añade 'onBack: () -> Unit' para la navegación.
    //patient: Patient,
    doctorViewModel: DoctorViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    // TODO: TAREA 1 y 4 - Estado y Eventos con MVVM
    // 1. Obtén el 'uiState' del ViewModel.
    // 2. Define el paciente a mostrar: uiState.value.selectedPatient (o uno por defecto si es null).
    // 3. Define las lambdas para los eventos:
    //    - onPainValueChange: llama a viewModel.onPainLevelChanged
    //    - onNoteValueChange: llama a viewModel.onNoteChanged

    val scrollState = rememberScrollState()

    //PARA EL TOAST
    val context = LocalContext.current

    //Aqui poner con by y no =, si no el slider y las notas no se pueden interactuar
    val uiState by doctorViewModel.uiState.collectAsState()

    val patient = uiState.selectedPatient ?: return

    //Coger la fun onPainLevelChanged del doctorViewModel
    val onPainValueChange: (Float) -> Unit = {newLevel ->
        doctorViewModel.onPainLevelChanged(newLevel)
    }

    //Coger la fun onNoteChanged del doctorViewModel
    val onNoteValueChange: (String) -> Unit = {newNote ->
        doctorViewModel.onNoteChanged(newNote)
    }


    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    )  {

        // TODO 1: Mejora el estilo de este texto (usa MaterialTheme o un componente personalizado como DetailSectionTitle)
        Text("Datos Identificativos")

        // TODO 1: Implementa correctamente este componente
        PatientSummaryCard(patient = patient)

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // TODO 1: Mejora el estilo de este texto (usa MaterialTheme o un componente personalizado como DetailSectionTitle)
        Text(
            text = "Sintomatología",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        PatientSymptomatology(
            patient = patient,
            onPainValueChange = onPainValueChange
        )

        // TODO 1: Mejora el estilo de este texto (usa MaterialTheme o un componente personalizado como DetailSectionTitle)
        Text(
            text = "Diagnóstico",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // TODO: Este componente se entrega ya implementado, solo tienes que llamarlo con los parámetros adecuados.
        PatientDiagnosis(
            patient = patient,
            onValueChange = onNoteValueChange
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            /* TODO: Actualizar los datos */
                doctorViewModel.onVisitSaved()
                Toast.makeText(
                    context,
                    "Se ha actualizado el paciente",
                    Toast.LENGTH_SHORT
                ).show()
                onBack()
            }
        ) {
            Text("Actualizar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PatientDetailScreenPreview(){

    val patientPreview = PatientRepository.getPatients().first()

    DoctorRoundTheme() {

    }
}