package net.iessochoa.rubenexposito.pex_kebab_kiosk

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.KebabTopAppBar
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.navigation.KebabNavHost
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.navigation.KebabOrderDestination
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.navigation.OrderDetailDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KebabKioskApp(
    viewModel: KebabKioskViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val currentScreenTitle = when (currentRoute) {
        KebabOrderDestination.route -> stringResource(KebabOrderDestination.titleRes)
        OrderDetailDestination.route -> stringResource(OrderDetailDestination.titleRes)
        else -> stringResource(R.string.app_name)
    }

    val canNavigateBack = navController.previousBackStackEntry != null
    Scaffold(
        topBar = {
            KebabTopAppBar(
                title = currentScreenTitle,
                canNavigateBack = canNavigateBack,
                navigateUp = {navController.navigateUp()}
            )
        }
    ) { innerPadding ->
        KebabNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}