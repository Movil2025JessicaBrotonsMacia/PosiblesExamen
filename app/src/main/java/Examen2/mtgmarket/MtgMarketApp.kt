package net.iessochoa.sergiocontreras.mtgmarket

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.mtgmarket.data.MtgRepository
import net.iessochoa.sergiocontreras.mtgmarket.ui.ReviewScreen
import net.iessochoa.sergiocontreras.mtgmarket.ui.getConditionMessage
import net.iessochoa.sergiocontreras.mtgmarket.ui.theme.MtgMarketTheme
import net.iessochoa.sergiocontreras.mtgmarket.ui.theme.Typography


/** Aquí encontrarás el Scaffold
    convenientemente lo separamos de lo que sería el composable de la vista  por lo tanto es el lugar para tocar el
    - topbar
    - snackbar
    - fab
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    var rating by remember { mutableStateOf(0) }
    var showAllCards by remember { mutableStateOf(false) }
    var selectedCard by remember { mutableStateOf("") }

    val cardList = MtgRepository.getAllCards().map { it.name }

    //CONTEXT ACTUAL
    val context = LocalContext.current

    Scaffold(
        /* TODO topBar: Centrar el topBar y dar el estilo Typograpy headlineLarge de nuestro paquete. */
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ReviewScreen",
                        style = Typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },

        /* TODO programar lógica del onClick del FAB.
            TODO Sustituir el text del FAB por el icon + text */
        floatingActionButton = {
            ExtendedFloatingActionButton(
                // Mensaje Carta $carta revisada: estado $estadomensaje
                onClick = {
                    val condition = getConditionMessage(rating)
                    Toast.makeText(
                        context,
                        "Carta $selectedCard revisada: estado $condition",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            ) {
                Icon(Icons.Default.Star, contentDescription = null)
                Text("Review")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ReviewScreen(
            modifier = Modifier.padding(innerPadding),
            showAllCards = showAllCards,
            onShowAllCardsChanged = { showAllCards = it },
            selectedCard = selectedCard,
            cardList = cardList,
            onValueChangedEvent = {
                selectedCard = it
                rating = 0
                                  },
            rating = rating,
            onRatingChanged = { rating = it }
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun LocalAppPreview() {
    MtgMarketTheme() {
        App()
    }
}