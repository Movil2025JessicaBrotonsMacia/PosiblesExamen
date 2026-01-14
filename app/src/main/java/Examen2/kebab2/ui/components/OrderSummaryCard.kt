package net.iessochoa.jessicabrotons.kebab2.ui.components

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.jessicabrotons.kebab2.data.OrderRepository
import net.iessochoa.jessicabrotons.kebab2.model.KebabOrder
import net.iessochoa.jessicabrotons.kebab2.ui.theme.Kebab2Theme


/*
COMPONENTE REUTILIZABLE PARA MOSTRAR LOS PEDIDOS
 */
@Composable
fun OrderSummaryCard(
    order: KebabOrder,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO EXAMEN
    // Mostrar resumen en Row
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick //SI NO TIENE ESTO NO PODEMOS HACER CLICK!!!!!!!!!!!
    ){
        Row(
            modifier = modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //INFORMACIÓN A LA IZQ
            Column(
                modifier = Modifier.weight(1f)
                    .padding(8.dp)
            ) {
                //Nombre del cliente
                Text(
                    text = "Cliente: ${order.customerName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                //Que ha pedido
                Text(
                    text = order.kebabType,
                    style = MaterialTheme.typography.titleSmall
                )
                //Precio del pedido
                Text(
                    text = "Precio: ${order.price}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            //ESTADO A LA DER
            Column(
                modifier = Modifier.weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Estado del pedido: ${order.status}"
                )
            }
        }

    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PatientSummaryCardPreview() {
//    val orderPreview = OrderRepository.getOrders().first()
//    Kebab2Theme {
//        OrderSummaryCard(
//            order = orderPreview,
//            modifier = Modifier.padding(16.dp)
//        )
//    }
//}