package net.iessochoa.rubenexposito.pex_kebab_kiosk

import net.iessochoa.rubenexposito.pex_kebab_kiosk.data.ToppingRepository
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.BreadType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.MeatType
import net.iessochoa.rubenexposito.pex_kebab_kiosk.model.Topping

data class KebabKioskUiState(
    val availableBreads: List<BreadType> = BreadType.entries,
    val availableMeats: List<MeatType> = MeatType.entries,
    val availableToppings: List<Topping> = ToppingRepository.getAllToppings(),


    val selectedBread: BreadType? = null,
    val selectedMeat: MeatType = MeatType.MIXED,
    val selectedToppings: Set<Topping> = emptySet(),
    val totalPrice: Double  = 0.0
)