package net.iessochoa.jessicabrotons.clientekebab.ui.navigation

import net.iessochoa.jessicabrotons.posibleexamen.R


interface NavigationDestination{
    val route: String
    val titleRes: Int
}

object KebabMainDestination : NavigationDestination {
    override val route = "kebab_main"
    override val titleRes = R.string.main_screen
}

object KebabOrderDestination : NavigationDestination {
    override val route = "kebab_order"
    override val titleRes = R.string.order_screen
}

object KebabListDestination : NavigationDestination {
    override val route = "kebab_list"
    override val titleRes = R.string.list_screen
}
//
//object KebabShowOrderDestination : NavigationDestination {
//    override val route = "kebab_showOrder"
//    override val titleRes = R.string.list_showOrder
//}


