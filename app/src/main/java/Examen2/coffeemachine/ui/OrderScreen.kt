package net.iessochoa.sergiocontreras.coffeemachine.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.coffeemachine.App
import net.iessochoa.sergiocontreras.coffeemachine.data.CoffeeRepository
import net.iessochoa.sergiocontreras.coffeemachine.model.Coffee
import net.iessochoa.sergiocontreras.coffeemachine.ui.components.CoffeeList
import net.iessochoa.sergiocontreras.coffeemachine.ui.components.CoffeeSelector
import net.iessochoa.sergiocontreras.coffeemachine.ui.theme.CoffeeMachineTheme


@Composable
fun OrderScreen(modifier: Modifier = Modifier) {

    /* TODO: Esto cambiarlo por todos los cafés */
    //OBTENER TODOS LOS CAFÉS
    val coffees = CoffeeRepository.getCoffees()
    
    //OBTENER CAFÉ SELECCCIONADO (POR DEFECTO EL PRIMERO)
    var selectedCoffee by remember { mutableStateOf(coffees.first()) }

    /*TODO: Deberás usar el café seleccionado y el dinero introducido para calcular. */
    //var change: Double = calculateChange(1.8, 5.0)
    var change by remember { mutableDoubleStateOf(0.0) }

    var isCoffeeOrdered by remember { mutableStateOf(false) }
    //IMPORTE
    var amountString by remember { mutableStateOf("") }

    //CONTEXT ACTUAL
    val context = LocalContext.current



    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE8D5)))
        {

        /* TODO: Esto hay que hacer un componente reutilizable
        *   y sacar todos los cafés en la máquina no solo uno para ello emplea las clases CoffeeList
        * (todos los cafés) y CoffeeSelector (la fila de selección radio + texto + imagen) en .ui.components
        * */
            CoffeeList(
                coffees,
                selectedCoffee,
                {selectedCoffee = it}
            )

            Spacer(modifier = modifier.padding(8.dp))


        Payment(
            amountString = amountString,
            onAmountChange = {amountString = it}
        )
        Row(
            modifier = Modifier
        ) {

            /* TODO QUE SALGA UN TOAST CON EL CAMBIO AL HACER CLICK
            *   text = ¡Café servido!", y duration = Toast.LENGTH_SHORT*/
            Button(onClick = {
                val amount = amountString.toDoubleOrNull()

                if (amount != null){
                    change = calculateChange(
                        selectedCoffee.productPrice,
                        amount
                    )
                    isCoffeeOrdered = true

                    Toast.makeText(
                        context,
                        R.string.cafe_servido,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text(stringResource(R.string.realizar_pedido))
            }
        }

        // Ahora mismo a fuego le estoy pasando el cambio, esto esta mal
        Change (
            isCoffeeOrdered = isCoffeeOrdered,
            change = change
        )
    }
}

@Composable

fun Payment(
    amountString: String,
    onAmountChange: (String) -> Unit
    ) {

    //var amountString by remember { mutableStateOf("") }

    OutlinedTextField(
        value = amountString,
        onValueChange = onAmountChange,
        label = {Text(text = stringResource(R.string.importe_euro))},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )
    )
}

/* TODO modifica la firma del composable y pasale el valor táctico para que
    si el botón se ha pulsado una vez se muestre el bloque correcto y en caso contrario
    esperando café.
 */
@Composable
fun Change(
    isCoffeeOrdered: Boolean,
    change: Double
) {
    //val context = LocalContext.current
    if (!isCoffeeOrdered){
        Text(text = stringResource(R.string.esperando))
    } else {
        Text(text = stringResource(R.string.disfruta_cafe))
        Text(text = stringResource(R.string.cambio, change))
    }
}

fun calculateChange(price: Double, amount: Double): Double {
    return amount - price
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    CoffeeMachineTheme {
        App()
    }
}

