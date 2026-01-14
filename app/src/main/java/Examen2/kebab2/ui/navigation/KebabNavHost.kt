package net.iessochoa.jessicabrotons.kebab2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.jessicabrotons.kebab2.ui.KebabViewModel
import net.iessochoa.jessicabrotons.kebab2.ui.screens.OrderDetailScreen
import net.iessochoa.jessicabrotons.kebab2.ui.screens.OrderListScreen


@Composable
fun KebabNavHost(
    navController: NavHostController,
    viewModel: KebabViewModel,
    modifier: Modifier = Modifier
) {
    // TODO EXAMEN
    NavHost(
        navController = navController,
        startDestination = OrderListDestination.route,
        modifier = modifier
    ){
        composable(route = OrderListDestination.route){
            OrderListScreen(
                viewModel = viewModel,
                onOrderClick = {selectedOrder ->
                    viewModel.onOrderSelected(selectedOrder)
                    navController.navigate(OrderDetailDestination.route)
                }
            )
        }

        composable(route = OrderDetailDestination.route){
            OrderDetailScreen(
                viewModel = viewModel,
                onSaveClick = {
                    viewModel.onOrderSaved()
                    navController.popBackStack()
                }
            )
        }
    }
}