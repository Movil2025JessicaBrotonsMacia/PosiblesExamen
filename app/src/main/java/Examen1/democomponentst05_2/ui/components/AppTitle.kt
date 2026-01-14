package net.iessochoa.sergiocontreras.democomponentst05_2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.Typography

@Composable
fun AppTitle(title: String, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = Typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}