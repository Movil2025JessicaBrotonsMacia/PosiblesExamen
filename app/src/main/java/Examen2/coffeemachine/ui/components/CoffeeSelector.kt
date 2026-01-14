package net.iessochoa.sergiocontreras.coffeemachine.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.coffeemachine.data.CoffeeRepository
import net.iessochoa.sergiocontreras.coffeemachine.model.Coffee

/** IMHO lo ideal es recibir un Coffee como **entrada**
 * y con eso sacar los datos que necesitamos lo dejo mal hecho a proposito pero con parte del scaffol
*/
@Composable
fun CoffeeSelector(
    coffee: Coffee,
    selected: Boolean,
    onSelected: () -> Unit
) {
    val unCoffeePorFavor = CoffeeRepository.getNextCoffee()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onSelected() }
        )
        Text(
            text = coffee.productName
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "${coffee.productPrice} €"
        )
        Spacer(modifier = Modifier.padding(36.dp))
        Image(
            painter = painterResource(coffee.productImage),
            contentDescription = coffee.productName,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoffeeSelectorPreview() {

    val sampleCoffee = Coffee(
        productName = "Café Solo",
        productPrice = 1.50,
        productImage = R.drawable.coffee_espresso,
        productDescription = ""
    )

    CoffeeSelector(
        coffee = sampleCoffee,
        selected = true,
        onSelected = {}
    )
}
