package net.iessochoa.sergiocontreras.democomponentst05_2.ui.components

import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.DemoComponentsT052Theme

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

@Composable
@Preview(showBackground = true)
fun RatingBarPreview(){
    RatingBar(
        currentRating = 3,
        onRatingChanged = {}
    )
}