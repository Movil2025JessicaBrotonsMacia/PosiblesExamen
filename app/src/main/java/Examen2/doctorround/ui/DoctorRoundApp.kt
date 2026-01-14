package net.iessochoa.sergiocontreras.doctorround.ui

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.doctorround.ui.navigation.DoctorRoundNavHost
import net.iessochoa.sergiocontreras.doctorround.ui.navigation.PatientDetailDestination
import net.iessochoa.sergiocontreras.doctorround.ui.navigation.PatientListDestination
import net.iessochoa.sergiocontreras.doctorround.ui.screens.PatientListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
/* TODO: Modifica esta clase para terminar de implementar la navegación */
fun DoctorRoundApp(
    viewModel: DoctorViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    //Variable para saber qué título de pantalla mostrar según la ruta donde nos encontremos.
    val currentScreenTitle = when (currentRoute) {
        PatientDetailDestination.route -> stringResource(PatientDetailDestination.titleRes)
        PatientListDestination.route -> stringResource(PatientListDestination.titleRes)
        else -> stringResource(R.string.app_name_doctorround)
    }

    //Saber si podemos volver atrás
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            //Aqui crear patientTopAppBar???? SI
//            TopAppBar(
//                title = { Text(currentScreenTitle) },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
//                )
//            )
            DoctorRoundTopAppBar(
                title = currentScreenTitle,
                canNavigateBack = canNavigateBack,
                navigateUp = {navController.navigateUp()}
            )
        },
        //AQUI SOLO PONDRIA EL FAB SI QUIERO QUE SE AÑADA UN BOTO NUEVO, SI QUIERO USAR UN BOTÓN QUE EXISTE NO
    ) { innerPadding ->
        // Aquí es donde el alumno implementará más tarde el NavHost
        // De momento, mostramos la lista directamente para que tengan algo visual
//        PatientListScreen(
//            modifier = Modifier.padding(innerPadding)
//        )

        DoctorRoundNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorRoundTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

