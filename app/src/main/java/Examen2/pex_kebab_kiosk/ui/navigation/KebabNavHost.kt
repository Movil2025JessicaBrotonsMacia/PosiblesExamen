package net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.rubenexposito.pex_kebab_kiosk.KebabKioskViewModel
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.screen.OrderSelectionScreen
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.screen.OrderSummaryScreen

@Composable
fun KebabNavHost(
    navController: NavHostController,
    viewModel: KebabKioskViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = KebabOrderDestination.route,
        modifier = modifier
    ) {
        composable(KebabOrderDestination.route) {
            OrderSelectionScreen(
                viewModel = viewModel,
                /*onConfirmOrder = {_,_,_->
                    navController.navigate(OrderDetailDestination.route)
                }*/
                onClick = {navController.navigate(OrderDetailDestination.route)}
            )
        }
        composable(OrderDetailDestination.route) {
            OrderSummaryScreen(
                viewModel = viewModel,
                onConfirmFinalOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack()
                }
            )
        }
    }
}