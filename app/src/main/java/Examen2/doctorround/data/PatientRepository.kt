package net.iessochoa.sergiocontreras.doctorround.data

import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.doctorround.model.Patient
import net.iessochoa.sergiocontreras.doctorround.model.PatientStatus

/**
 * Singleton. Repositorio simulado para la gestión de pacientes.
 * Actúa como fuente de verdad única (Single Source of Truth) para la app.
 */
object PatientRepository {

    // Lista mutable para simular persistencia en memoria durante el examen
    private val patients = mutableListOf(
        Patient(
            id = 1,
            fullName = "Geralt de Rivia",
            room = "Kaer-01",
            diagnosis = "Intoxicación por pociones y laceraciones múltiples",
            status = PatientStatus.STABLE,
            visitNote = "El metabolismo mutante acelera la curación. Requiere descanso.",
            painLevel = 3.5f,
            imageResId = R.drawable.geralt
        ),
        Patient(
            id = 2,
            fullName = "Yennefer de Vengerberg",
            room = "Suite Imperial",
            diagnosis = "Agotamiento mágico severo",
            status = PatientStatus.IMPROVING,
            visitNote = "",
            painLevel = 2.0f,
            imageResId = R.drawable.yennefer
        ),
        Patient(
            id = 3,
            fullName = "Rey Foltest",
            room = "Pabellón Real",
            diagnosis = "Herida incisa en cuello y ansiedad de estado",
            status = PatientStatus.CRITICAL,
            visitNote = "",
            painLevel = 7.5f,
            imageResId = R.drawable.foltest
        ),
        Patient(
            id = 4,
            fullName = "Jaskier (Dandelion)",
            room = "104-B",
            diagnosis = "Laringitis aguda y contusiones leves",
            status = PatientStatus.WORSENING,
            visitNote = "",
            painLevel = 9.5f,
            imageResId = R.drawable.jaskier
        ),
        Patient(
            id = 5,
            fullName = "Saskia (Saesenthessis)",
            room = "Sala Ignífuga",
            diagnosis = "Disfunción polimórfica y escamas resecas",
            status = PatientStatus.UNKNOWN,
            visitNote = "",
            painLevel = 0f,
            imageResId = R.drawable.ic_launcher_foreground //No visitada así que le meto el default
        ),
        Patient(
            id = 6,
            fullName = "Triss Merigold",
            room = "Torre-1",
            diagnosis = "Reacción alérgica severa a pociones mágicas",
            status = PatientStatus.IMPROVING,
            visitNote = "Evitar elixires. Tratamiento solo con remedios naturales.",
            painLevel = 4.0f,
            imageResId = R.drawable.triss
        )
    )

    /**
     * Devuelve la lista completa de pacientes.
     * @return Una lista inmutable de [Patient].
     */
    fun getPatients(): List<Patient> = patients

    /**
     * Actualiza un paciente en la lista.
     * Si el paciente existe, se reemplaza con el [updatedPatient].
     * @param updatedPatient El paciente con la información actualizada.
     */
    fun updatePatient(updatedPatient: Patient) {
        val index = patients.indexOfFirst { it.id == updatedPatient.id }
        if (index != -1) {
            patients[index] = updatedPatient
        }
    }

    /**
     * Devuelve un paciente según su ID.
     * @param id El ID único del paciente.
     * @return El [Patient] correspondiente al ID, o `null` si no se encuentra.
     */
    fun getPatientById(id: Int): Patient? = patients.find { it.id == id }
}