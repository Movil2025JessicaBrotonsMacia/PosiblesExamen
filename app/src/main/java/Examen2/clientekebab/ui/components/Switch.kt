package net.iessochoa.jessicabrotons.clientekebab.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.jessicabrotons.posibleexamen.R

@Composable
fun SwitchDelivery(
    //label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.label_switch),
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 18.sp
        )
        Spacer(Modifier.padding(horizontal = 10.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewSwitchDelivery(){
    SwitchDelivery(
        //label = stringResource(R.string.label_switch),
        checked = true,
        onCheckedChange = {  }
    )
}