package net.iessochoa.sergiocontreras.democomponentst05_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.democomponentst05_2.model.Game
import net.iessochoa.sergiocontreras.democomponentst05_2.data.getGameList
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.AppTitle
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.CustomDropdownMenu
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.GameCard
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.RatingBar
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.DemoComponentsT052Theme

@Composable
fun MainScreen() {

    val gameList = getGameList() // Cargamos la lista de juegos
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var currentRating by remember { mutableIntStateOf(0) }
    val onRatingChanged: (Int) -> Unit = {currentRating = it}
    var gameDifficulty by remember { mutableFloatStateOf(0f) }
    val onDifficultyChanged: (Float) -> Unit = {gameDifficulty = it}
    val gameListTitles = gameList.map { it.title } //Lista de String porq lo pide en options abajo
    var gameSelected by remember { mutableStateOf(gameListTitles[0]) }
    var game = gameList.find { it.title == gameSelected } ?: gameList[0] //Busca el juego que coincide, si no muestra el primero
    val onDropDownValueChange: (String) -> Unit = { gameSelected = it }


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {AppTitle("Game Review")},
        snackbarHost = {SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //muestra el SnackBar desde un hilo
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Me encanta el juego $gameSelected",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Mostrar Snackbar")
            }
        },
        //bottomBar = TODO(),
    ) { innerPadding ->
        Column {
            ReviewForm(
                gameList = gameList,
                game = game,
                gameSelected = gameSelected,
                onDropDownValueChange = onDropDownValueChange,
                modifier = Modifier
                    .padding(innerPadding),
                currentRating = currentRating,
                onRatingChanged = onRatingChanged,
                gameDifficulty = gameDifficulty,
                onDifficultyChanged = onDifficultyChanged
            )
            Spacer(modifier = Modifier.weight(1f)) //El trucazo de la separacion
            SummaryPanel(
                title = gameSelected,
                score = currentRating,
                difficulty = gameDifficulty.toInt()
            )
        }
    }

}

@Composable
fun ReviewForm(
    gameList: List<Game>,
    game: Game,
    gameSelected: String,
    onDropDownValueChange: (String) -> Unit,
    currentRating: Int,
    gameDifficulty: Float,
    onRatingChanged: (Int) -> Unit,
    onDifficultyChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre componentes
    ) {


        val gameListTitles = gameList.map { it.title } //Lista de String porq lo pide en options abajo

        Spacer(modifier = Modifier.height(16.dp))

        // Placeholder 1: DropDownMenu (Componente custom nuestro reutilizado)
        CustomDropdownMenu(
            options = gameListTitles,
            seleccion = gameSelected,
            label = stringResource(R.string.games_label),
            onValueChanged = onDropDownValueChange
        )

        //Esto aqui mal metido a fuego para que lo movamos donde toca
        GameCard(game = game)

        // Placeholder 2: RatingBar (Componente custom nuestro reutilizado)
        RatingBar(
            currentRating = currentRating,
            onRatingChanged = onRatingChanged
        )

        // Placeholder 3: Slider (Componente de Compose)
        Slider(
            value = gameDifficulty,
            valueRange = 1f..5f,
            steps = 5,
            onValueChange = onDifficultyChanged
        )


    }
}

@Composable
fun SummaryPanel(
    title: String,
    difficulty: Int,
    score: Int
) {
    Column {
        Text("Review para el juego $title")
        Text("Dificultad: $difficulty/5")
        Text("Puntuación: $score/5")
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    DemoComponentsT052Theme {
        MainScreen()
    }
}

