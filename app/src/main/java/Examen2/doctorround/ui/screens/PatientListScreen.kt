package net.iessochoa.sergiocontreras.doctorround.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.model.Patient
import net.iessochoa.sergiocontreras.doctorround.ui.DoctorViewModel
import net.iessochoa.sergiocontreras.doctorround.ui.components.PatientListCard
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme


@Composable
fun PatientListScreen(
    viewModel: DoctorViewModel = viewModel(),
    onPatientClick: (Patient) -> Unit,
    modifier: Modifier = Modifier,
    // TODO: TAREA 2 (MVVM) y TAREA 3 (Navegación) - Actualiza los parámetros:
    // 1. Añade el ViewModel para leer el estado (Tarea 2).
    // 2. Añade la lambda para navegar al detalle: onPatientClick: () -> Unit (Tarea 3).
) {
    // TODO: TAREA 2 - Migrar a MVVM
    // 1. BORRA la lectura directa del repositorio (línea de abajo).
    // 2. OBSERVA el estado del ViewModel (collectAsState) para obtener la lista de pacientes.

    //val patients = remember { PatientRepository.getPatients() } // <--- BORRAR ESTO

    //OBSERVAR EL ESTADO
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.patients) { patient ->
            PatientListCard(
                patient = patient,
                onClick = {
                    // TODO: TAREA 2 - Lógica de Selección
                    // - Avisa al ViewModel de qué paciente se ha seleccionado.
                    viewModel.onPatientSelected(patient)

                    // TODO: TAREA 3 - Navegación
                    // - Ejecuta la lambda de navegación para ir al detalle.
                    onPatientClick(patient)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PatientListScreenPreview() {
    DoctorRoundTheme {
    }
}