package net.iessochoa.jessicabrotons.kebab2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.kebab2.model.OrderStatus
import net.iessochoa.jessicabrotons.kebab2.ui.KebabViewModel
import net.iessochoa.jessicabrotons.kebab2.ui.components.OrderOptions
import net.iessochoa.jessicabrotons.kebab2.ui.components.StatusDropDown

@Composable
fun OrderDetailScreen(
    viewModel: KebabViewModel,
    onSaveClick: () -> Unit
) {
    // TODO EXAMEN
    OrderOptions(
        onBack = onSaveClick,
        viewModel = viewModel
    )

}