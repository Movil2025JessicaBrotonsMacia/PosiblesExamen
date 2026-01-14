/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.iessochoa.jessicabrotons.examen2.lunchtray


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen
import kotlinx.coroutines.flow.combine
import net.iessochoa.jessicabrotons.posibleexamen.R

import net.iessochoa.jessicabrotons.examen2.lunchtray.ui.CheckoutScreen



// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val title: Int){
    StartOrder(title = R.string.start_order), //Entrada
    Entree(title = R.string.choose_entree), //Menú de platos principales
    SideDish(title = R.string.choose_side_dish), //Menú de guarniciones
    Accompaniment(title = R.string.choose_accompaniment), //Menú de acompañamientos
    Confirmation(title = R.string.order_checkout) //Confirmación de la compra

}

// TODO: AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen, //enum
    canNavigateBack: Boolean, //se puede volver hacia atrás?
    navigateUp: () -> Unit, //ir hacia atras
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController() //Crear controlador de navegación
) {
    // TODO: Create Controller and initialization
    //Inicializar la entrada de la pila de actividades (Obtener la pantalla actual)
    val backStackEntry by navController.currentBackStackEntryAsState()

    //Obtener el nombre de la pantalla actual:
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.StartOrder.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            // TODO: AppBar
            LunchTrayAppBar(
                currentScreen = currentScreen,
                //Pila de pantallas por las que hemos ido pasando, la pantalla anterior a la actual.
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()} //Si es true, se hace esto
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.StartOrder.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = LunchTrayScreen.StartOrder.name){
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.Entree.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = LunchTrayScreen.Entree.name){
                val context = LocalContext.current
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onNextButtonClicked = {navController.navigate(LunchTrayScreen.SideDish.name)},
                    onSelectionChanged = {viewModel.updateEntree(it)},
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = LunchTrayScreen.SideDish.name){
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onNextButtonClicked = {navController.navigate(LunchTrayScreen.Accompaniment.name)},
                    onSelectionChanged = {viewModel.updateSideDish(it)},
                    modifier = Modifier.fillMaxHeight()
                )
            }

            composable(route = LunchTrayScreen.Accompaniment.name){
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onNextButtonClicked = {navController.navigate(LunchTrayScreen.Confirmation.name)},
                    onSelectionChanged = {viewModel.updateAccompaniment(it)},
                    modifier = Modifier.fillMaxHeight()
                )
            }

            composable(route = LunchTrayScreen.Confirmation.name){
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {navController.navigate(LunchTrayScreen.StartOrder.name)},
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
}
