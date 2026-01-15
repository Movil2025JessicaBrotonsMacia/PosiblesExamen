package net.iessochoa.rubenexposito.pex_kebab_kiosk.ui.navigation

import net.iessochoa.jessicabrotons.posibleexamen.R


interface NavigationDestination{
    val route: String
    val titleRes: Int
}
object KebabOrderDestination : NavigationDestination {
    override val route = "kebab_order"
    override val titleRes = R.string.realizar_orden
}

object OrderDetailDestination : NavigationDestination {
    override val route = "order_detail"
    override val titleRes = R.string.detalle_de_la_orden
}
