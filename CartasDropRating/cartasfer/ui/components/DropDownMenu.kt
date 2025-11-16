package net.iessochoa.jessicabrotons.cartasfer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.cartasfer.R

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DropDown(
//    onSelected: (String) -> Unit   //Para que cada selección muestre una carta
//) {
//    val list = stringArrayResource(R.array.lista)
//    var selectedChoice by remember { mutableStateOf(list[0]) }
//    var isExpanded by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp)
//            .padding(top = 50.dp, bottom = 20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        ExposedDropdownMenuBox(
//            expanded = isExpanded,
//            onExpandedChange = { isExpanded = !isExpanded }
//        ) {
//            TextField(
//                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
//                value = selectedChoice,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = {
//                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
//                }
//            )
//
//            ExposedDropdownMenu(
//                expanded = isExpanded,
//                onDismissRequest = { isExpanded = false }
//            ) {
//                list.forEach { option ->
//                    DropdownMenuItem(
//                        text = { Text(option) },
//                        onClick = {
//                            selectedChoice = option
//                            onSelected(option)   // ➜ Avisamos al padre
//                            isExpanded = false
//                        }
//
//                    )
//                }
//            }
//        }
//
////        Text(
////            "Currently selected: $selectedChoice",
////            modifier = Modifier.padding(bottom = 20.dp)
////        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    selected: String,              // ⬅ Estado viene del padre
    onSelected: (String) -> Unit   // ⬅ El padre actualiza el estado
) {
    val list = stringArrayResource(R.array.lista)
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(top = 50.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
                value = selected,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onSelected(option)    // ⬅ Avisamos al padre
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

