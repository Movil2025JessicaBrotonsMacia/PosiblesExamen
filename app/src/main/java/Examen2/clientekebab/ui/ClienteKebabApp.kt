package net.iessochoa.jessicabrotons.clientekebab.ui

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
import kotlinx.coroutines.flow.compose
import net.iessochoa.jessicabrotons.clientekebab.ui.navigation.KebabListDestination
import net.iessochoa.jessicabrotons.clientekebab.ui.navigation.KebabMainDestination
import net.iessochoa.jessicabrotons.clientekebab.ui.navigation.KebabNavHost
import net.iessochoa.jessicabrotons.clientekebab.ui.navigation.KebabOrderDestination
import net.iessochoa.jessicabrotons.posibleexamen.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteKebabApp(
    viewModel: ClienteKebabViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val currentScreenTitle = when (currentRoute){
        KebabMainDestination.route -> stringResource(KebabMainDestination.titleRes)
        KebabOrderDestination.route -> stringResource(KebabOrderDestination.titleRes)
        KebabListDestination.route -> stringResource(KebabListDestination.titleRes)
        else -> stringResource(R.string.app_name_kebab)
    }

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