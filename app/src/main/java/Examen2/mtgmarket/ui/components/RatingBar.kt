package net.iessochoa.sergiocontreras.mtgmarket.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Rating bar composable
 * @param maxRating número de estrellas
 * @param currentRating rating actual
 * @param onRatingChanged callback para cuando se cambia el rating
 * @param iconSelect icono para seleccionado
 * @param iconUnSelect icono para no seleccionado
 * @param modifier modificador
 * @param color color de las estrellas
 */

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    iconSelect: ImageVector = Icons.Filled.Star,
    iconUnSelect: ImageVector = Icons.Outlined.Star,
    modifier: Modifier = Modifier,
    color: Color = Color.Red
) {
    // 1. Aplica el modifier del parámetro al componente raíz (el Row)
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) iconSelect else iconUnSelect,
                contentDescription = "Star $i",
                // 2. Crea un Modifier nuevo y local para cada Icon
                modifier = Modifier.clickable {
                    onRatingChanged(
                        if (i == 1 && currentRating == 1) 0 else i
                    )
                },
                tint = if (i <= currentRating) color else Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingBarPreview() {
    var (rating, onRatingChanged) = remember { mutableStateOf(3) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RatingBar(
            currentRating = rating,
            onRatingChanged = onRatingChanged,
            modifier = Modifier.size(40.dp)
        )
    }
}