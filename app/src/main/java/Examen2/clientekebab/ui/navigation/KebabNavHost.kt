package net.iessochoa.jessicabrotons.clientekebab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder
import net.iessochoa.jessicabrotons.clientekebab.ui.ClienteKebabViewModel
import net.iessochoa.jessicabrotons.clientekebab.ui.screens.MainScreen
import net.iessochoa.jessicabrotons.clientekebab.ui.screens.OrderScreen
import net.iessochoa.jessicabrotons.clientekebab.ui.screens.ResumeScreen
import kotlin.math.max

@Composable
fun KebabNavHost(
    navController: NavHostController,
    viewModel: ClienteKebabViewModel,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = KebabMainDestination.route,
        modifier = modifier,
    ) {
        composable(route = KebabMainDestination.route){
            MainScreen(
                onGoToOrderScreen = {
                    navController.navigate(KebabOrderDestination.route)
                },
                onGoToOrdersListScreen = {
                    navController.navigate(KebabListDestination.route)
                }
            )
        }

        composable(route = KebabOrderDestination.route){
            OrderScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = KebabListDestination.route){
            ResumeScreen(
                viewModel = viewModel,
                onOrderClick = { orderSelected ->
                    viewModel.onOrderSelected(orderSelected)
                    navController.navigate(KebabOrderDestination.route)
                }
            )
        }

    }
}