package net.iessochoa.sergiocontreras.doctorround.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.sergiocontreras.doctorround.data.PatientRepository
import net.iessochoa.sergiocontreras.doctorround.model.Patient

class DoctorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorUiState())
    val uiState: StateFlow<DoctorUiState> = _uiState.asStateFlow()

    // Bloque de inicialización para cargar los pacientes
    init {
        _uiState.update { currentState ->
            currentState.copy(
                patients = PatientRepository.getPatients()
            )
        }
    }

    fun onPatientSelected(patient: Patient) {
        // TODO: Actualiza el estado con el paciente seleccionado, hacer click en el paciente
        _uiState.update { currentState ->
            currentState.copy(
                selectedPatient = patient
            )
        }
    }

    fun onNoteChanged(newNote: String) {
        // TODO: Actualiza la nota en el estado
        _uiState.update { currentState ->
            //Esto lo hacemos porque patient es inmutable y debemos usar copy x2 para actualizar el estado
            val updatedPatient = currentState.selectedPatient?.copy(
                visitNote = newNote
            )
            currentState.copy(
                selectedPatient = updatedPatient
            )
         }
    }

    fun onPainLevelChanged(newLevel: Float) {
        // TODO: Actualiza el nivel de dolor
        _uiState.update { currentState ->
            val updatedPatient = currentState.selectedPatient?.copy(
                painLevel = newLevel
            )
            currentState.copy(
                selectedPatient = updatedPatient
            )
        }
    }

    fun onVisitSaved() {
        // TODO (Opcional): Simula la persistencia de datos llamando a PatientRepository.updatePatient()
        //  con los nuevos valores de dolor y nota de visita para el paciente seleccionado

        //No pongo _uiState.update porque me obliga a devolver un DoctorUiState y no queremos devolver nada.
        val currentPatient = _uiState.value.selectedPatient

        if(currentPatient != null){
            PatientRepository.updatePatient(currentPatient)
        }
    }
}

