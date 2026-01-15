package net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.MeatType

@Composable
fun MeatSelector(
    meats: List<MeatType>,
    selectedMeat: MeatType?,
    onMeatSelected: (MeatType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        meats.forEach { meat ->
            val isSelected = (meat == selectedMeat)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { onMeatSelected(meat) }
                    .padding(4.dp)
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { onMeatSelected(meat) }
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = meat.displayName, // Usa "Ternera", "Pollo", etc.
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}