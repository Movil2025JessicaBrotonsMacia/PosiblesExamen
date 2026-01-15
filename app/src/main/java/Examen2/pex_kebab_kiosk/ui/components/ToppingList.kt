package net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.Topping

@Composable
fun ToppingList(
    availableToppings: List<Topping>,
    selectedToppings: Set<Topping>,
    onToppingToggle: (Topping) -> Unit,
    //onToppingToggle: (Topping, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        availableToppings.forEach { topping ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedToppings.contains(topping),
                    onCheckedChange = { onToppingToggle(topping) }
                    /*onCheckedChange = { newCheckedState ->
                        onToppingToggle(topping, newCheckedState) }*/
                )
                Column {
                    Text(text = topping.name, style = MaterialTheme.typography.bodySmall)
                    if (topping.price > 0) {
                        Text(
                            text = "+${String.format("%.2f", topping.price)}€",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                   
                }
            }
        }
    }
}