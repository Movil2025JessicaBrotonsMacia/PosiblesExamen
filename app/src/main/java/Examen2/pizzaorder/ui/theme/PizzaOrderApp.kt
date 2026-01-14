package Examen2.pizzaorder.ui.theme//package net.iessochoa.jessicabrotons.pizzaorder.ui.theme
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import net.iessochoa.jessicabrotons.pizzaorder.ui.PizzaViewModel
//import net.iessochoa.jessicabrotons.pizzaorder.ui.screen.OrderListScreen
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//        /* TODO: Completa esta clase para implementar correctamente la navegación */
//fun PizzaOrderApp() {
//
//    // TODO 1: Crear el ViewModel correctamente
//    val viewModel: PizzaViewModel = viewModel()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    // TODO 2: Cambiar el título según la pantalla
//                    Text("Pizza Express")
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
//                )
//                // TODO 3: Añadir botón de volver SOLO en detalle
//            )
//        }
//    ) { innerPadding ->
//
//        // TODO 4: Sustituir esta pantalla por un NavHost
//        // De momento mostramos la lista para que haya algo visible
//        OrderListScreen(
//            viewModel = viewModel,
//            modifier = Modifier.padding(innerPadding),
//            onPizzaClick = {
//                // TODO 5: Navegar a la pantalla de detalle
//            }
//        )
//    }
//}