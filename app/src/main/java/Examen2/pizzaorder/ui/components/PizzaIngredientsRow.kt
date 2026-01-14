package net.iessochoa.jessicabrotons.pizzaorder.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
        /* TODO: No se actualiza bien la selección */
fun PizzaIngredientsRow(
    ingredients: List<String>,
    selectedIngredients: List<String>,
    onIngredientToggle: (String) -> Unit
) {
    Row {
        ingredients.forEach { ingredient ->
            PizzaIngredientChip(
                name = ingredient,
                selected = false, // ❌ ERROR INTENCIONAL
                onClick = {
                    onIngredientToggle(ingredient)
                }
            )
        }
    }
}
