package net.iessochoa.jessicabrotons.clientekebab.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.clientekebab.model.KebabOrder
import net.iessochoa.jessicabrotons.clientekebab.model.ProductSize
import net.iessochoa.jessicabrotons.clientekebab.model.ProductType
import net.iessochoa.jessicabrotons.clientekebab.model.Vegetables

@Composable
fun OrderListCard(
    kebabOrder: KebabOrder,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onClick()}
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
                .padding(horizontal = 8.dp)
            ) {
                Text(
                    "Pedido Nº: ${kebabOrder.id}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    "Cliente: ${kebabOrder.clientName}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text("Producto: ${kebabOrder.productType} ${kebabOrder.size}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text("Precio: ${kebabOrder.basePrice} €",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier.weight(1f)
                .padding(horizontal = 8.dp)
            ) {
                Text("Ingredientes: ${kebabOrder.vegetables}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewOrderListCard(){
//    val fakeOrder = KebabOrder(
//        id = 1,
//        productType = ProductType.DURUM,
//        clientName = "Jessica",
//        vegetables = listOf(
//            Vegetables.TOMATE,
//            Vegetables.LECHUGA
//        ),
//        size = ProductSize.MEDIUM,
//        basePrice = 6.5,
//        notes = "Sin cebolla",
//        delivery = true
//    )
//
//    OrderListCard(
//        kebabOrder = fakeOrder,
//        onClick =
//    )
//}