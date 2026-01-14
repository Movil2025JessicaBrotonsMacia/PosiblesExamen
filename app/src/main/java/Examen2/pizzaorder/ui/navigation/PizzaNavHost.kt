package net.iessochoa.jessicabrotons.pizzaorder.ui.navigation

import net.iessochoa.jessicabrotons.pizzaorder.ui.PizzaViewModel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.jessicabrotons.pizzaorder.ui.screen.OrderDetailScreen
import net.iessochoa.jessicabrotons.pizzaorder.ui.screen.OrderListScreen

@Composable
fun PizzaNavHost(
    navController: NavHostController,
    viewModel: PizzaViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = OrderListDestination.route,
        modifier = modifier
    ) {
        composable(route = OrderListDestination.route) {
            OrderListScreen(
                viewModel = viewModel,
                onOrderClick = { selectedOrder ->
                    viewModel.onOrderSelected(selectedOrder)
                    navController.navigate(OrderDetailDestination.route)
                }
            )
        }

        composable(route = OrderDetailDestination.route) {
            OrderDetailScreen(
                viewModel = viewModel,
                onSave = {
                    viewModel.onSaveOrder()
                    navController.popBackStack()
                         },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
