package net.iessochoa.jessicabrotons.kebab2.ui

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.iessochoa.jessicabrotons.kebab2.ui.navigation.KebabNavHost
import net.iessochoa.jessicabrotons.kebab2.ui.navigation.OrderDetailDestination
import net.iessochoa.jessicabrotons.kebab2.ui.navigation.OrderListDestination
import net.iessochoa.jessicabrotons.posibleexamen.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KebabApp(
    viewModel: KebabViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // TODO EXAMEN
    // Crear NavController
    // Llamar a KebabNavHost

    // Trucazo 🤙: Observamos la ruta actual para cambiar el título de la TopBar
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Lógica para decidir qué título mostrar según la ruta donde estemos
    val currentScreenTitle = when (currentRoute) {
        OrderListDestination.route -> stringResource(OrderListDestination.titleRes)
        OrderDetailDestination.route -> stringResource(OrderDetailDestination.titleRes)
        else -> stringResource(R.string.app_name)
    }

    // Lógica para saber si podemos volver atrás (para mostrar la flechita)
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            KebabOrderTopAppBar(
                title = currentScreenTitle,
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        // Aquí llamamos a nuestro NavHost personalizado
       KebabNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KebabOrderTopAppBar(
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