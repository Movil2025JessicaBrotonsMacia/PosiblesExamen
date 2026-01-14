package net.iessochoa.jessicabrotons.clientekebab.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder
import net.iessochoa.jessicabrotons.clientekebab.ui.ClienteKebabViewModel
import net.iessochoa.jessicabrotons.clientekebab.ui.components.OrderListCard

@Composable
fun ResumeScreen(
    viewModel: ClienteKebabViewModel = viewModel(),
    onOrderClick: (KebabOrder) -> Unit,
    modifier: Modifier = Modifier
){
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.orders) { order ->
            OrderListCard(
                kebabOrder = order,
                onClick = {
                    //Avisa al viewModel de que order ha sido seleccionada
                    viewModel.onOrderSelected(order)
                    //Ejecuta la lambda de navegación para ir a editar el order
                    onOrderClick(order)
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewResumeScreen() {
//    val fakeViewModel = ClienteKebabViewModel()
//
//    ResumeScreen(
//        viewModel = fakeViewModel
//    )
//}
