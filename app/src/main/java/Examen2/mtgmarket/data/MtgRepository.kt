package net.iessochoa.sergiocontreras.mtgmarket.data

import net.iessochoa.jessicabrotons.posibleexamen.R
import net.iessochoa.sergiocontreras.mtgmarket.model.MtgCard

/**
 * Repositorio Singleton que simula una fuente de datos para cartas de Magic: The Gathering.
 *
 * Esta clase actúa como un stub o driver para proveer datos falsos (fake data), lo que es útil
 * para el desarrollo y las pruebas iniciales sin necesidad de una base de datos real o una API de red.
 * Contiene una lista estática de objetos [MtgCard] predefinidos.
 *
 * Se utiliza 'object' para implementar el patrón Singleton, asegurando que solo exista una única
 * instancia de este repositorio en toda la aplicación.
 * A futuro, se podría convertir en una 'class' para facilitar la inyección de dependencias
 * (por ejemplo, para inyectarle un DAO de Room).
 */
object MtgRepository {


    private val mtgCards = listOf(
        MtgCard(
            name = "Horn of Gondor",
            text = "When Horn of Gondor enters the battlefield, create a 1/1 white Human Soldier creature token. 3, T: Create X 1/1 white Human Soldier creature tokens, where X is the number of Humans you control.",
            rarity = "Rare",
            expansionSet = "LTR",
            imageId = R.drawable.horn_of_gondor
        ),
        MtgCard(
            name = "Land Tax",
            text = "At the beginning of your upkeep, if an opponent controls more lands than you, you may search your library for up to three basic land cards, reveal them, put them into your hand, then shuffle.",
            rarity = "Mythic",
            expansionSet = "CMM",
            imageId = R.drawable.land_tax
        ),
        MtgCard(
            name = "Mirkwood Spider",
            text = "Deathtouch. Whenever Mirkwood Spider attacks, target legendary creature you control gains deathtouch until end of turn. Far and wide Shelob\'''s lesser broods spread from glen to glen, to Dol Guldur and the fastnesses of Mirkwood.",
            rarity = "Common",
            expansionSet = "LTR",
            imageId = R.drawable.mirkwood_spider
        ),
        MtgCard(
            name = "Oliphaunt",
            text = "Trample. Whenever Oliphaunt attacks, another target creature you control gets +2/+0 and gains trample until end of turn. Mountaincycling 1 (1, Discard this card: Search your library for a Mountain card, reveal it, put it into your hand, then shuffle.)",
            rarity = "Common",
            expansionSet = "LTR",
            imageId = R.drawable.oliphaunt
        ),
        MtgCard(
            name = "Tsabo's Decree",
            text = "Choose a creature type. Target player reveals his or her hand and discards all creature cards of that type from it. Then destroy all creatures of that type that player controls. They can\'''t be regenerated.",
            rarity = "Rare",
            expansionSet = "INV",
            imageId = R.drawable.tsabo_decree
        ),
        MtgCard(
            name = "Zombie Infestation",
            text = "Discard two cards from your hand: Put a 2/2 black Zombie creature token into play.",
            rarity = "Uncommon",
            expansionSet = "ODY",
            imageId = R.drawable.zombie_infestation
        )
    )

    /**
     * Devuelve la lista completa de todas las cartas de MTG almacenadas en el repositorio.
     *
     * @return Una [List] de [MtgCard] que contiene todas las cartas disponibles.
     */
    fun getAllCards(): List<MtgCard> {
        return mtgCards
    }

    /**
     * Selecciona y devuelve una única carta de MTG del repositorio de forma aleatoria.
     *
     * @return Un objeto [MtgCard] seleccionado al azar de la lista interna.
     */
    fun getRandomCard(): MtgCard {
        return mtgCards.random()
    }
}