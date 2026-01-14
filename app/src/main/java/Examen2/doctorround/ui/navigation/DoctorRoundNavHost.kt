package net.iessochoa.sergiocontreras.doctorround.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.sergiocontreras.doctorround.ui.DoctorViewModel
import net.iessochoa.sergiocontreras.doctorround.ui.screens.PatientDetailScreen
import net.iessochoa.sergiocontreras.doctorround.ui.screens.PatientListScreen

// Añade aquí tus imports necesarios (NavHost, composable, tus screens, tu ViewModel...)

/**
 * Grafo de navegación de la aplicación.
 * Aquí se define qué pantalla se muestra para cada ruta.
 */
@Composable
fun DoctorRoundNavHost(
    // TODO: TAREA 3 - Añade los parámetros necesarios:
    // 1. navController (NavHostController)
    // 2. viewModel (DoctorViewModel) - Para compartir el estado entre pantallas
    navController: NavHostController,
    viewModel: DoctorViewModel,
    modifier: Modifier = Modifier
) {
    // TODO: TAREA 3 - Implementa el NavHost
    // - Define el startDestination usando tu objeto PatientListDestination
    // - Define los dos composables:
    //      1. PatientListDestination.route -> Muestra PatientListScreen
    //      2. PatientDetailDestination.route -> Muestra PatientDetailScreen

    /* PISTA: Recuerda usar el Navigation Helper si te atascas */

    NavHost(
        navController = navController,
        startDestination = PatientListDestination.route,
        modifier = modifier
    ){
        //Pantalla 1 -> PatientListScreen
        composable (route = PatientListDestination.route){
            PatientListScreen(
                viewModel = viewModel,
                onPatientClick = {pacienteSeleccionado ->
                    // 1. Guardamos la selección en el ViewModel
                    viewModel.onPatientSelected(pacienteSeleccionado)
                    // 2. Navegamos al detalle
                    navController.navigate(PatientDetailDestination.route)
                }
            )
        }

        //Pantalla 2 -> PatientDetailScreen
        composable (route = PatientDetailDestination.route){
            PatientDetailScreen(
                //Al usar el mismo viewModel, la pantalla ya sabe que paciente se seleccionó
                doctorViewModel = viewModel,
                //Evento para volver atrás
                onBack = { navController.popBackStack() }
            )
        }
    }
}