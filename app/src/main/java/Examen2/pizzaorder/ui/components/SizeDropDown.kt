package net.iessochoa.jessicabrotons.pizzaorder.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeDropdown(
    selected: PizzaSize,
    options: List<PizzaSize>,
    onSizeSelected: (PizzaSize) -> Unit,
    modifier: Modifier = Modifier
) {
    // ❌ No hay estado expanded
    // ❌ No se cierra el menú
    // ❌ No convierte a enum

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selected.name,
            onValueChange = {},
            label = { Text(text = "Selecciona un tamaño") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true) // Ancla el menú al TextField
                .fillMaxWidth()
        )
        // El menú desplegable en sí
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Acción al cerrar el menú
        ) {
            // Itera sobre las opciones para crear cada item del menú
            options.forEach { option: PizzaSize ->
                DropdownMenuItem(
                    text = { Text(text = option.name) },
                    onClick = {
                        expanded = false // Cierra el menú
                       onSizeSelected(option) // Llama al callback con la opción seleccionada
                    }
                )
            }
        }
    }
}