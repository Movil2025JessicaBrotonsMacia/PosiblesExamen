package net.iessochoa.sergiocontreras.mtgmarket.ui

import android.R.attr.layoutDirection
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.mtgmarket.App
import net.iessochoa.sergiocontreras.mtgmarket.data.MtgRepository
import net.iessochoa.sergiocontreras.mtgmarket.model.MtgCard
import net.iessochoa.sergiocontreras.mtgmarket.ui.components.DynamicSelectTextField
import net.iessochoa.sergiocontreras.mtgmarket.ui.components.MtgCardItem
import net.iessochoa.sergiocontreras.mtgmarket.ui.components.RatingBar
import net.iessochoa.sergiocontreras.mtgmarket.ui.theme.MtgMarketTheme

@Composable
fun ReviewScreen(
    showAllCards: Boolean,
    onShowAllCardsChanged: (Boolean) -> Unit,
    selectedCard: String,
    cardList: List<String>,
    onValueChangedEvent: (String) -> Unit,
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    val listaCartas = MtgRepository.getAllCards()

    val selectedMtgCard = listaCartas.find { it.name == selectedCard }


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        /* TODO Switch: Gestiona la lógica del switch y su presentación */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text= "Show all cards")
            Spacer(modifier = Modifier.padding(8.dp))
            Switch(
                checked = showAllCards,
                onCheckedChange = onShowAllCardsChanged
            )
        }

        /* TODO Dropdown: reemplaza este texto por una llamada al dropdown (lo tienes en components) recibiendo toda la lista de tarjetas
            haz que toda dependiendo del valor del switch se muestre o no */
        if (!showAllCards){
            DynamicSelectTextField(
                selectedValue = selectedCard,
                options = cardList,
                label = "Selecciona una carta",
                onValueChangedEvent = onValueChangedEvent
            )

            if(selectedMtgCard != null){ //Si no es nulo, muestrame la carta de la lista
                MtgCardItem(card = selectedMtgCard)

                /* TODO RatingBar: Reemplazar este componente por una rating bar que consistirá en
                1 fila donde tenemos un texto que dice "Condition: " acompañando a la propia RatingBar
                El estado hay que transmitirlo al FAB.
             */
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Condition: ")
                    RatingBar(
                        currentRating = rating,
                        onRatingChanged = onRatingChanged
                    )
                }
            }
        } else{
            /* TODO MtgCardItem: crea el componente reusable MtgCardItem (en .ui.components)
                    *   Este componente reusable tendrá 2 filas
                    *   Fila uno el titulo de la carta
                    *   Fila 2 la imagen de la carta y a la derecha su texto, para completar el rectángulo*/

            /* TODO LazyColum: pasale como items la lista de todas las tarjetas a LazyColumn o la tarjeta seleccionada*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(
                        start = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateStartPadding(layoutDirection),
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(layoutDirection),
                    )
            ) {
                items(listaCartas) { carta ->
                    //val singleMtgCard = MtgRepository.getRandomCard()
                    MtgCardItem(card = carta)
                }
            }
        }
    }
}

fun getConditionMessage(rating: Int): String {
    when(rating){
        1 -> return "Poor"
        2 -> return "Played"
        3 -> return "Good"
        4 -> return "Excellent"
        5 -> return "Mint / Near Mint"
        else -> return "Poor"
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    MtgMarketTheme {
        App()
    }
}