package net.iessochoa.jessicabrotons.t05ejercicios.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.jessicabrotons.t05ejercicios.ui.theme.T05EjerciciosTheme



@Composable
fun SeleccionColores(modifier: Modifier = Modifier){

    var sliderValueRed by remember { mutableFloatStateOf(0f) }
    val onColorChangedRed: (Float) -> Unit = {sliderValueRed = it}

    var sliderValueGreen by remember { mutableFloatStateOf(0f) }
    val onColorChangedGreen: (Float) -> Unit = {sliderValueGreen = it}

    var sliderValueBlue by remember { mutableFloatStateOf(0f) }
    val onColorChangedBlue: (Float) -> Unit = {sliderValueBlue = it}

    Column (
        modifier
            .background(color = Color(0xFFFFF8EC))
    ){
        Text(
            text = stringResource(R.string.tituloEjercicio2),
            modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 40.dp, bottom = 60.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        MostrarSlider(
            sliderValueRed,
            onColorChangedRed,
            sliderValueGreen,
            onColorChangedGreen,
            sliderValueBlue,
            onColorChangedBlue
        )
        ShowColor(sliderValueRed, sliderValueGreen, sliderValueBlue)
    }
}

@Composable
fun MostrarSlider(
    sliderValueRed: Float,
    onColorChangeRed: (Float) -> Unit,
    sliderValueGreen: Float,
    onColorChangeGreen: (Float) -> Unit,
    sliderValueBlue: Float,
    onColorChangeBlue: (Float) -> Unit,
    modifier: Modifier = Modifier
){


    Column {
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "R",
                modifier
                    .padding(5.dp),
                fontSize = 30.sp
            )
            Slider(
                value = sliderValueRed,
                valueRange = 1f..255f,
                steps = 255,
                onValueChange = onColorChangeRed
            )
        }
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "G",
                modifier
                    .padding(5.dp),
                fontSize = 30.sp
            )
            Slider(
                value = sliderValueGreen,
                valueRange = 1f..255f,
                steps = 255,
                onValueChange = onColorChangeGreen
            )
        }
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "B",
                modifier
                    .padding(5.dp),
                fontSize = 30.sp
            )
            Slider(
                value = sliderValueBlue,
                valueRange = 1f..255f,
                steps = 255,
                onValueChange = onColorChangeBlue
            )
        }
    }

    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Color: (${sliderValueRed.toInt()}, ${sliderValueGreen.toInt()}, ${sliderValueBlue.toInt()})",
            modifier
                .padding(top = 20.dp),
            fontSize = 30.sp
        )
    }

}

@Composable
fun ShowColor(
    redColor: Float,
    greenColor: Float,
    blueColor: Float
){
    Box(modifier = Modifier
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp))
        .fillMaxSize()
        .background(Color(redColor.toInt(), greenColor.toInt(), blueColor.toInt()))
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ColorsPreview(){
    T05EjerciciosTheme {
        SeleccionColores()
    }
}

