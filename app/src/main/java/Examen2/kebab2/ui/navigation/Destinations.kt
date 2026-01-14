package net.iessochoa.jessicabrotons.kebab2.ui.navigation

import net.iessochoa.jessicabrotons.posibleexamen.R


interface Destinations {
    val route: String
    val titleRes: Int
}

object OrderListDestination : Destinations {
    override val route = "order_list"
    override val titleRes = R.string.app_name
}

object OrderDetailDestination : Destinations {
    override val route = "order_detail"
    override val titleRes = R.string.order_detail
}