package net.iessochoa.jessicabrotons.cafeteralazy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.cafeteralazy.model.Cafes

@Composable
fun Card(
    carta: Cafes
){

    Card(modifier = Modifier) {
        Column {
            Image(
                painter = painterResource(carta.imagen),
                contentDescription = carta.nombre.toString(),
                modifier = Modifier
                    .padding(10.dp) //padding dentro de la carta
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}