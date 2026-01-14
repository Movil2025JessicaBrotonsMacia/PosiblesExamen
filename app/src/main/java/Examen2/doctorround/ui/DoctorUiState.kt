package net.iessochoa.sergiocontreras.doctorround.ui

import net.iessochoa.sergiocontreras.doctorround.model.Patient

/**
 * Estado de la UI para la pantalla del listado y detalle de pacientes.
 *
 * @param patients La lista de pacientes a mostrar.
 * @param selectedPatient El paciente actualmente seleccionado, `null` si no hay ninguno.
 */
data class DoctorUiState(
    val patients: List<Patient> = emptyList(),
    val selectedPatient: Patient? = null
)
