package net.iessochoa.jessicabrotons.clientekebab.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.iessochoa.jessicabrotons.clientekebab.model.ProductType
import net.iessochoa.jessicabrotons.posibleexamen.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTypeDropDown(
    selected: ProductType,
    options: List<ProductType>,
    onProductSelected: (ProductType) -> Unit,
    modifier: Modifier = Modifier
){

    var expanded by remember{ mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {expanded = !expanded},
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selected.name,
            onValueChange = {}, //Porque solo es lectura
            label = { Text(stringResource(R.string.label_producto)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option: ProductType ->
                DropdownMenuItem(
                    text = {Text(text = option.name)},
                    onClick = {
                        expanded = false
                        onProductSelected(option)
                    }
                )
            }
        }
    }

}

