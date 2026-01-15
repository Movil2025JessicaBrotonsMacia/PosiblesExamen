package net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.rubenexposito.pex_kebab_kiosk.data.ToppingRepository
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.BreadSelector
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.KebabTopAppBar
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.MeatSelector
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.ToppingList

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.rubenexposito.pex_kebab_kiosk.KebabKioskViewModel
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.BreadType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSelectionScreen(
    viewModel: KebabKioskViewModel,
    //onConfirmOrder: (String, String, List<String>) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val breadOptions: List<BreadType> = BreadType.values().toList()


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 1. SECCION PAN
        SectionTitle("1. Elige tu pan")
        BreadSelector(
            breads = breadOptions,
            selectedBread = uiState.selectedBread,
            onBreadSelected = { newBread ->
                viewModel.onBreadSelect(newBread)
            }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(top = 16.dp))


        //2. SECCION CARNE
        SectionTitle("2. Elige la Carne")
        MeatSelector(
            meats = uiState.availableMeats,
            selectedMeat = uiState.selectedMeat,
            onMeatSelected = { newMeat ->
               viewModel.onMeatSelect(newMeat)
            }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(top = 16.dp))

        //3. SECCION SALSAS Y EXTRAS
        SectionTitle("3. Salsas y Extras")
        ToppingList(
            selectedToppings = uiState.selectedToppings,
            availableToppings = uiState.availableToppings,
            onToppingToggle = { topping ->
                viewModel.toggleTopping(topping)
            },
            /*onToppingToggle = { topping, isChecked ->
                viewModel.toggleTopping(topping, isChecked)
                }*/
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Button(
            onClick = {
                /*onConfirmOrder(
                    uiState.selectedBread?.displayName ?: "",
                    uiState.selectedMeat?.displayName ?: "",
                    uiState.selectedToppings.map { it.name },
                )*/
                //viewModel.onConfirmOrder()
                viewModel.setTotal()
                onClick()
            },
        ) {
            Text("Confirmar Pedido")
        }
    }
}
@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}