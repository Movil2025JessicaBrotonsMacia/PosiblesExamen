package net.iessochoa.sergiocontreras.democomponentst05_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.democomponentst05_2.model.Game

/**
 * Tarjeta personalizada para mostrar la información de un Juego.
 */
@Composable
fun GameCard(game: Game, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Título del juego
            Text(
                text = game.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Imagen del juego
            Image(
                painter = painterResource(id = game.imageRes),
                contentDescription = "Carátula de ${game.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción corta
            Text(
                text = game.shortDescription,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}