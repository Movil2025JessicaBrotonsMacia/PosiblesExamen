package net.iessochoa.jessicabrotons.pizzaorder.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.pizzaorder.data.OrderRepository
import net.iessochoa.jessicabrotons.pizzaorder.model.PizzaOrder
import net.iessochoa.jessicabrotons.pizzaorder.model.totalPrice
import net.iessochoa.jessicabrotons.pizzaorder.ui.theme.PizzaOrderTheme

@Composable
fun OrderListCard(
    order: PizzaOrder,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Pedido de ${order.customerName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.pizzaType,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "A pagar: ${order.totalPrice()}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun OrderListCardPreview(){
//  val order = OrderRepository.getOrders().first()
//    PizzaOrderTheme() {
//        OrderListCard(
//            order = order,
//            onClick = {}
//        )
//    }
//}