package net.iessochoa.jessicabrotons.pizzaorder.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaOrder
import net.iessochoa.jessicabrotons.pizzaorder.ui.navigation.OrderDetailDestination
import net.iessochoa.jessicabrotons.pizzaorder.ui.navigation.OrderListDestination
import net.iessochoa.jessicabrotons.pizzaorder.ui.navigation.PizzaNavHost
import net.iessochoa.jessicabrotons.pizzaorder.ui.screen.OrderListScreen
import net.iessochoa.jessicabrotons.posibleexamen.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /* TODO: Implementar la navegación completa de la aplicación */
fun PizzaOrderApp(
    viewModel: PizzaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

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
            PizzaOrderTopAppBar(
                title = currentScreenTitle,
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        /*
         * TODO:
         * Sustituir esta pantalla por un NavHost
         *
         * - Pantalla 1: PizzaListScreen
         * - Pantalla 2: PizzaDetailScreen
         *
         * NOTA:
         * De momento mostramos la lista directamente
         * para que la app compile y tenga contenido visual
         */
//        OrderListScreen(
//            viewModel = viewModel,
//            modifier = Modifier.padding(innerPadding),
//            onPizzaClick = {
//                // TODO:
//                // 1. Avisar al ViewModel del pedido seleccionado
//                // 2. Navegar al detalle
//            }
//        )

        PizzaNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaOrderTopAppBar(
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