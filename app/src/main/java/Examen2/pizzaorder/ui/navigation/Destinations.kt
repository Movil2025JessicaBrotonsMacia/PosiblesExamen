package net.iessochoa.jessicabrotons.pizzaorder.ui.navigation

import net.iessochoa.jessicabrotons.posibleexamen.R


interface NavigationDestination {
    val route: String
    val titleRes: Int
}

object OrderListDestination : NavigationDestination{
    override val route = "order_list"
    override val titleRes = R.string.app_name
}


object OrderDetailDestination : NavigationDestination {
    override val route = "order_detail"
    override val titleRes = R.string.order_detail
}