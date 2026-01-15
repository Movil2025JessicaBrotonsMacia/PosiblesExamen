package net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.BreadType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.MeatType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.Topping
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.KebabTopAppBar

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components.AllergensBar
import net.iessochoa.rubenexposito.pex_kebab_kiosk.KebabKioskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSummaryScreen(
    viewModel: KebabKioskViewModel,
    onConfirmFinalOrder: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val bread = uiState.selectedBread
    val meat = uiState.selectedMeat
    val toppings = uiState.selectedToppings
    val totalPrice = uiState.totalPrice

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Casi listo!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Sección de Base
                Text(
                    "Base",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text("Pan: ${bread?.displayName ?: "No seleccionado"}")
                Text("Carne: ${meat.displayName}")

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                // Sección de Extras
                Text(
                    "Extras y Salsas",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (toppings.isEmpty()) {
                    Text("Sin extras seleccionados", style = MaterialTheme.typography.bodyMedium)
                } else {
                    toppings.forEach { topping ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("• ${topping.name}")
                            if (topping.price > 0) {
                                Text("+${String.format("%.2f", topping.price)}€")
                            }
                        }
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                // Precio Final
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total a pagar:", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = "${String.format("%.2f", totalPrice)}€",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de Alérgenos (Agregados)
        val allAllergens = toppings.flatMap { it.allergens }.distinct()
        if (allAllergens.isNotEmpty()) {
            Text(
                text = "Alérgenos e intolerancias detectados:",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            AllergensBar(allergens = allAllergens)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de Finalización
        Button(
            onClick = {
                onConfirmFinalOrder()
                //viewModel.loadInitialData()
                Toast.makeText(
                    context,
                    "Se ha confirmado el pedido con éxito",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("PAGAR Y FINALIZAR", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
