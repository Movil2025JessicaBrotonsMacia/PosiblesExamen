package net.iessochoa.sergiocontreras.coffeemachine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.MainScope
import net.iessochoa.sergiocontreras.coffeemachine.ui.OrderScreen
import net.iessochoa.sergiocontreras.coffeemachine.ui.theme.CoffeeMachineTheme
import net.iessochoa.sergiocontreras.coffeemachine.ui.theme.Typography


/* Aquí encontrarás el Scaffold
    convenientemente lo separamos de lo que sería el composable de la vista
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            /* TODO: Esto hay que cambiarlo para centrarlo y darle headlineLarge de estilo*/
            TopAppBar(
                title = { Text(
                    "Coffee Machine",
                    style = Typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                ) }
            )
        }
    ) { innerPadding ->
        OrderScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }

}