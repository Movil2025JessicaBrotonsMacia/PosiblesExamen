package net.iessochoa.jessicabrotons.clientekebab.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.clientekebab.model.ProductSize
import net.iessochoa.jessicabrotons.clientekebab.model.ProductType
import net.iessochoa.jessicabrotons.clientekebab.model.Vegetables
import net.iessochoa.jessicabrotons.clientekebab.model.productPrice
import net.iessochoa.jessicabrotons.clientekebab.ui.ClienteKebabViewModel
import net.iessochoa.jessicabrotons.clientekebab.ui.components.NameTextField
import net.iessochoa.jessicabrotons.clientekebab.ui.components.OrderNotes
import net.iessochoa.jessicabrotons.clientekebab.ui.components.ProductTypeDropDown
import net.iessochoa.jessicabrotons.clientekebab.ui.components.SalsasDropDown
import net.iessochoa.jessicabrotons.clientekebab.ui.components.SizeRadioButton
import net.iessochoa.jessicabrotons.clientekebab.ui.components.SwitchDelivery
import net.iessochoa.jessicabrotons.clientekebab.ui.components.VegetablesCheckBox
import net.iessochoa.jessicabrotons.posibleexamen.R

@Composable
fun OrderScreen(
    viewModel: ClienteKebabViewModel,
    onBack: () -> Unit,
){
    val uiState by viewModel.uiState.collectAsState()

//    val onProductChanged: (ProductType) -> Unit = {newProduct ->
//        viewModel.onProductSelected(newProduct)
//    }
//
//    val onSizeChanged: (ProductSize) -> Unit = { newSize ->
//        viewModel.onSizeSelected(newSize)
//    }
//
//    val onIngredientChanged: (Vegetables) -> Unit = { newIngredient ->
//        viewModel.onIngredientSelected(newIngredient)
//    }
//
//    val onDeliverChanged: (Boolean) -> Unit = { enabled ->
//        viewModel.onDeliverChanged(enabled)
//    }
//
//    val onSalsasChanged: (String) -> Unit = { newSalsa ->
//        viewModel.onSalsasChanged(newSalsa)
//    }
//
//    val onNotesChanged: (String) -> Unit = { newNotes ->
//        viewModel.onNoteChanged(newNotes)
//    }

    val productTypes: List<ProductType> = ProductType.values().toList()

    val listaVegetables: List<Vegetables> = Vegetables.values().toList()

    val listaSalsas: List<String> = stringArrayResource(R.array.salsas).toList()

    var listaSize: List<ProductSize> = ProductSize.values().toList()

    val context = LocalContext.current

    val totalPrice = uiState.currentOrder.productPrice()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            NameTextField(
                kebabOrder = uiState.currentOrder,
                onValueChange = { viewModel.onNameChanged(it) }
            )
        }

        item {
            ProductTypeDropDown(
                selected = uiState.currentOrder.productType,
                options = productTypes,
                onProductSelected = { viewModel.onProductSelected(it) }
            )
        }

            //Spacer(Modifier.padding(vertical = 6.dp))

        item {
            VegetablesCheckBox(
                listaOpciones = listaVegetables,
                vegetableSeleccionado = uiState.currentOrder.vegetables.toSet(),
                onOptionChecked = { viewModel.onIngredientSelected(it) }
            )
        }
            //Spacer(Modifier.padding(vertical = 6.dp))

        item {
            SalsasDropDown(
                selected = uiState.currentOrder.salsas,
                options = listaSalsas,
                onSalsaSelected = { viewModel.onSalsasChanged(it) }
            )
        }

            //Spacer(Modifier.padding(vertical = 6.dp))

        item {
            SizeRadioButton(
                listaSize = listaSize,
                sizeSeleccionado = uiState.currentOrder.size,
                onSizeSeleccionado = { viewModel.onSizeSelected(it) }
            )
        }

            //Spacer(Modifier.padding(vertical = 6.dp))

        item {
            SwitchDelivery(
                checked = uiState.currentOrder.delivery,
                onCheckedChange = { enabled -> viewModel.onDeliverChanged(enabled) }
            )
        }

            //Spacer(Modifier.padding(vertical = 6.dp))

        item {
            OrderNotes(
                kebabOrder = uiState.currentOrder,
                onValueChange = { viewModel.onNoteChanged(it) }
            )
        }

            //Spacer(Modifier.padding(vertical = 6.dp))

        item {
            Text(
                text = "Precio: ${totalPrice}",
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    /* TODO: Actualizar los datos */
                    viewModel.onOrderSaved()
                    Toast.makeText(
                        context,
                        "Se ha guardado el pedido",
                        Toast.LENGTH_SHORT
                    ).show()
                    onBack()
                }
            ) {
                Text("Guardar Pedido")
            }
        }
    }
}

class PreviewClienteKebabViewModel : ClienteKebabViewModel() {
    init {
        
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOrderScreen() {
    OrderScreen(
        viewModel = PreviewClienteKebabViewModel(),
        onBack = {}
    )
}


