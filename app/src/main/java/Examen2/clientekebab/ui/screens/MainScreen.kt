package net.iessochoa.jessicabrotons.clientekebab.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.jessicabrotons.clientekebab.ui.ClienteKebabViewModel
import net.iessochoa.jessicabrotons.clientekebab.ui.theme.ClienteKebabTheme
import net.iessochoa.jessicabrotons.posibleexamen.R

/*
Aquí si mostrara Text("Pedidos realizados: ${uiState.orders.size}")
sí que necesitaria hacer un viewmodel
 */
@Composable
fun MainScreen(
    onGoToOrderScreen: () -> Unit,
    onGoToOrdersListScreen: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Botón para realizar el pedido
        Button(
            onClick = onGoToOrderScreen
        ) {
            Text(
                stringResource(R.string.realizar_pedido),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 22.sp
            )

        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        //Botón para ver los pedidos realizados
        Button(
            onClick = onGoToOrdersListScreen
        ) {
            Text(
                stringResource(R.string.ver_pedido),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 22.sp
            )
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun previewMainScreen(){
//    ClienteKebabTheme() {
//        MainScreen(modifier = Modifier)
//    }
//}