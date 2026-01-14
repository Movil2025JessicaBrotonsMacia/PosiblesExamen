package net.iessochoa.sergiocontreras.thedogwalker.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.ui.navigation.DogDetailDestination
import net.iessochoa.sergiocontreras.thedogwalker.ui.navigation.DogListDestination
import net.iessochoa.sergiocontreras.thedogwalker.ui.navigation.DogWalkerNavHost
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogWalkerApp(
    viewModel: DogWalkerViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
    ) {

    // Trucazo 🤙: Observamos la ruta actual para cambiar el título de la TopBar
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    //esto es para mostrar el nombre del perro en el titulo de detalle
    val uiState by viewModel.uiState.collectAsState()

    // Lógica para decidir qué título mostrar según la ruta donde estemos
    val currentScreenTitle = when (currentRoute) {
        DogListDestination.route -> stringResource(DogListDestination.titleRes)
//        DogDetailDestination.route -> stringResource(DogDetailDestination.titleRes)
        DogDetailDestination.route -> uiState.selectedDog?.let { "Detalle de ${it.name}" } ?: "Detalle"
        else -> stringResource(R.string.app_name_dog)
    }

    // Lógica para saber si podemos volver atrás (para mostrar la flechita)
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { DogWalkerTopAppBar(
            title = currentScreenTitle,
            canNavigateBack = canNavigateBack,
            navigateUp = { navController.navigateUp() }
        ) }
    ) { innerPadding ->
        DogWalkerNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogWalkerTopAppBar(
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
                        contentDescription = null
                    )
                }
            }
        }
    )
}

